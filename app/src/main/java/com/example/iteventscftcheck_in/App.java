package com.example.iteventscftcheck_in;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static TeamCFTAPI teamCFTAPI;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://team.cft.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        teamCFTAPI = retrofit.create(TeamCFTAPI.class);
    }

    public static TeamCFTAPI getApi() {
        return teamCFTAPI;
    }
}