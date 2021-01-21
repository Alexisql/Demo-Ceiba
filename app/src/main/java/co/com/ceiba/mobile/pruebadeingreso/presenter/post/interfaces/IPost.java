package co.com.ceiba.mobile.pruebadeingreso.presenter.post.interfaces;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.User;

public interface IPost {
    interface View {
        void showPosts(ArrayList<Post> posts);
    }
    interface Presenter{
        void showPosts(ArrayList<Post> posts);
        void findPosts(User user);
    }
    interface Interactor{
        void findPosts(User user);
    }
}
