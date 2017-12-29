package org.com.inep.gitusers.control;

import android.util.Log;

import org.com.inep.gitusers.model.User;
import org.com.inep.gitusers.model.service.GitApiClient;
import org.com.inep.gitusers.model.service.GitApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shiki on 28/12/2017.
 */
public class UserController implements Callback<User> {

    public void searchUser(String login) {
        GitApiInterface apiClient = GitApiClient.getClient();

        Call<User> call = apiClient.searchUser(login);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if(response.isSuccessful()) {
            User result = response.body();

            Log.d("FOR DEBUG - SUCCESS", String.valueOf(result.toString()));
        } else {
            Log.d("FOR DEBUG - ERROR ", String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Log.d("FOR DEBUG - FAILURE", String.valueOf(t.getMessage()));
    }

}
