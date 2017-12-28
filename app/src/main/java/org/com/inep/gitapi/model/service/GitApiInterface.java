package org.com.inep.gitapi.model.service;

import org.com.inep.gitapi.model.Repository;
import org.com.inep.gitapi.model.User;
import org.com.inep.gitapi.model.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Shiki on 27/12/2017.
 */
public interface GitApiInterface {

    @GET("search/users")
    Call<UserList> searchUsers(@Query("q") String searchFor);

    @GET("users/{userLogin}")
    Call<User> searchUser(@Path("userLogin") String userLogin);

    @GET("users/{userLogin}/repos")
    Call<List<Repository>> requestRepositories(@Path("userLogin") String userLogin);

}
