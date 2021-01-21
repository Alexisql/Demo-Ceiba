package co.com.ceiba.mobile.pruebadeingreso.model.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.user.interfaces.IUser;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiAdapter;
import co.com.ceiba.mobile.pruebadeingreso.utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInteractor implements IUser.Interactor, Callback<List<User>> {

    private List<User> users;
    private IUser.Presenter presenter;
    private Context context;

    public UserInteractor(IUser.Presenter presenter, Context context) {
        this.presenter = presenter;
        users = new ArrayList<>();
        this.context = context;
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()) {
            users = response.body();
            if (users != null) {
                presenter.showUsers((ArrayList<User>) users);
                new AsyncSaveUserBD(context, new AsyncSaveUserBD.CallbackBD() {
                    @Override
                    public void result(String msj) {
                        Toast.makeText(context, msj, Toast.LENGTH_SHORT).show();
                    }
                }).execute((ArrayList<User>) users);
            } else {
                Log.e("onResponseUsers", "Response is null");
            }
            Util.dismissDailog();
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Util.dismissDailog();
        Log.e("getServicesFailed", "Response failed");
    }

    @Override
    public void findUsers() {
        new AsyncGetUserBD(context, new AsyncGetUserBD.CallbackBD() {
            @Override
            public void result(List<User> users) {
                initUserList((ArrayList<User>) users);
            }
        }).execute();
    }

    private void initUserList(ArrayList<User> usersList) {
        if (usersList.isEmpty()) {
            Call<List<User>> call = ApiAdapter.getApiService().getUsers();
            call.enqueue(this);
        } else {
            users = usersList;
            presenter.showUsers((ArrayList<User>) users);
            Util.dismissDailog();
        }
    }

    public static class AsyncGetUserBD extends AsyncTask<String, Void, ArrayList<User>> {

        Context context;
        private CallbackBD callback;

        public AsyncGetUserBD(Context context, CallbackBD callback) {
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected ArrayList<User> doInBackground(String... strings) {
            return (ArrayList<User>) Util.getUsersBD(context);
        }

        @Override
        protected void onPostExecute(ArrayList<User> users) {
            super.onPostExecute(users);
            callback.result(users);
        }

        public interface CallbackBD {
            void result(List<User> users);
        }
    }

    public static class AsyncSaveUserBD extends AsyncTask<ArrayList<User>, Void, String> {

        private Context context;
        private CallbackBD callback;

        public AsyncSaveUserBD(Context context, CallbackBD callback) {
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected String doInBackground(ArrayList<User>... users) {
            Util.saveUserBD(context, users[0]);
            return context.getString(R.string.msj_result_bd);
        }

        @Override
        protected void onPostExecute(String msj) {
            super.onPostExecute(msj);
            callback.result(msj);
        }

        public interface CallbackBD {
            void result(String msj);
        }
    }

}
