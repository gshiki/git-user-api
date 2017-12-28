package org.com.inep.gitapi.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shiki on 27/12/2017.
 */
public class GitApiClient {

    public static String CLIENT_BASE_URL = "https://api.github.com/";

    public static GitApiInterface getClient() {

        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(CLIENT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient).build();

        return retrofit.create(GitApiInterface.class);
    }
}
