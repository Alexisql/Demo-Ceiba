package co.com.ceiba.mobile.pruebadeingreso.presenter.post.presenter;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.User;
import co.com.ceiba.mobile.pruebadeingreso.model.interactor.PostInteractor;
import co.com.ceiba.mobile.pruebadeingreso.presenter.post.interfaces.IPost;

public class PostPresenter implements IPost.Presenter {

    private IPost.View view;

    public PostPresenter(IPost.View view) {
        this.view = view;
    }

    @Override
    public void showPosts(ArrayList<Post> posts) {
        view.showPosts(posts);
    }

    @Override
    public void findPosts(User user) {
        IPost.Interactor interactor;
        interactor = new PostInteractor(this);
        interactor.findPosts(user);
    }
}
