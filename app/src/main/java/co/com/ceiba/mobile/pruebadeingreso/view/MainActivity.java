package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.User;
import co.com.ceiba.mobile.pruebadeingreso.adapter.AdapterEmpty;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserAdapter;
import co.com.ceiba.mobile.pruebadeingreso.presenter.user.interfaces.IUser;
import co.com.ceiba.mobile.pruebadeingreso.presenter.user.presenter.UserPresenter;
import co.com.ceiba.mobile.pruebadeingreso.utils.Util;

public class MainActivity extends Activity implements IUser.View {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    AdapterEmpty adapterEmpty;
    EditText textSearch;
    List<User> listUserTmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();

        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not Necessary
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not Necessary
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.getDialog() == null)
            Util.showDialog(this);
    }

    private void initElements() {
        Util.showDialog(this);
        textSearch = findViewById(R.id.editTextSearch);
        recyclerView = findViewById(R.id.recyclerViewSearchResults);
        IUser.Presenter presenter;
        presenter = new UserPresenter(this, getApplicationContext());
        presenter.findUsers();
    }

    @Override
    public void showUsers(ArrayList<User> users) {
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        userAdapter = new UserAdapter(users);
        recyclerView.setAdapter(userAdapter);
        listUserTmp = users;
    }

    private void filter(String textSearch) {
        List<User> filterUser = new ArrayList<>();
        userAdapter = new UserAdapter(filterUser);
        recyclerView.setAdapter(userAdapter);
        for (User user : listUserTmp) {
            if (user.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                filterUser.add(user);
            }
        }
        if (filterUser.isEmpty()) {
            adapterEmpty = new AdapterEmpty();
            recyclerView.setAdapter(adapterEmpty);
        } else {
            userAdapter.filterList(filterUser);
        }
    }

}