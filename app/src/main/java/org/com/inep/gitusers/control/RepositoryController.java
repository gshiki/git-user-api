package org.com.inep.gitusers.control;

import android.util.Log;

import org.com.inep.gitusers.model.Repository;
import org.com.inep.gitusers.model.service.GitApiClient;
import org.com.inep.gitusers.model.service.GitApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shiki on 28/12/2017.
 */
public class RepositoryController implements Callback<List<Repository>> {

    public void requestRepositories(String userLogin) {
        GitApiInterface apiClient = GitApiClient.getClient();

        Call<List<Repository>> call = apiClient.requestRepositories(userLogin);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
        if(response.isSuccessful()) {
            List<Repository> result = response.body();

            Log.d("FOR DEBUG - SUCCESS", String.valueOf(result.toString()));
        } else {
            Log.d("FOR DEBUG - ERROR ", String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<List<Repository>> call, Throwable t) {
        Log.d("FOR DEBUG - FAILURE", String.valueOf(t.getMessage()));
    }

}
