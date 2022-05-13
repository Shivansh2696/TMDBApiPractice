package com.example.tmdbapi;

import android.app.Application;

import com.example.tmdbapi.RoomDB.DataBase.AppDatabase;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase database= AppDatabase.getInstance(this);
    }
}
