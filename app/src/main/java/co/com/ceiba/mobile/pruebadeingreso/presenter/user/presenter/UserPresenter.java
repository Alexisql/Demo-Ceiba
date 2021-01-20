package co.com.ceiba.mobile.pruebadeingreso.presenter.user.presenter;

import android.content.Context;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.User;
import co.com.ceiba.mobile.pruebadeingreso.model.interactor.UserInteractor;
import co.com.ceiba.mobile.pruebadeingreso.presenter.user.interfaces.IUser;

public class UserPresenter implements IUser.Presenter {

    private IUser.View view;
    private Context context;

    public UserPresenter(IUser.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void findUsers() {
        IUser.Interactor interactor;
        interactor = new UserInteractor(this, context);
        interactor.findUsers();
    }

    @Override
    public void showUsers(ArrayList<User> users) {
        view.showUsers(users);
    }

}
