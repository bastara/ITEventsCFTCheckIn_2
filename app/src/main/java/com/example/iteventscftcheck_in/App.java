package com.example.iteventscftcheck_in;

import android.app.Application;

import androidx.room.Room;

import com.example.iteventscftcheck_in.db.DatabaseHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static TeamCFTAPI teamCFTAPI;
    private Retrofit retrofit;


    private static App instance;
    private DatabaseHelper db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://team.cft.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        teamCFTAPI = retrofit.create(TeamCFTAPI.class);

        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), DatabaseHelper.class, "database_23")
                 .allowMainThreadQueries()
                 .build();
    }

    public static TeamCFTAPI getApi() {
        return teamCFTAPI;
    }

    public DatabaseHelper getDatabaseInstance() {
        return db;
    }
}