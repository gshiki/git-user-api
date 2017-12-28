package org.com.inep.gitapi.control;

import android.util.Log;

import org.com.inep.gitapi.model.UserList;
import org.com.inep.gitapi.model.service.GitApiClient;
import org.com.inep.gitapi.model.service.GitApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shiki on 27/12/2017.
 */
public class UsersController implements Callback<UserList> {

    public void searchUsers(String query) {
        GitApiInterface apiClient = GitApiClient.getClient();

        Call<UserList> call = apiClient.searchUsers(query);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UserList> call, Response<UserList> response) {
        if(response.isSuccessful()) {
            UserList result = response.body();

            Log.d("FOR DEBUG - SUCCESS", String.valueOf(result.toString()));
        } else {
            Log.d("FOR DEBUG - ERROR ", String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<UserList> call, Throwable t) {
        Log.d("FOR DEBUG - FAILURE", String.valueOf(t.getMessage()));
    }

}
