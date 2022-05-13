package com.example.tmdbapi.RoomDB.DataBase;

import android.content.Context;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.tmdbapi.RoomDB.DAOs.NowPlayingMoviesDao;
import com.example.tmdbapi.RoomDB.DAOs.PopularMoviesDao;
import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;
import com.example.tmdbapi.TMDB.Models.PopularMovies.PopularMoviesResultPojo;

import retrofit2.Converter;

@Database(entities = {NowPlayingMoviesResultsPojo.class, PopularMoviesResultPojo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NowPlayingMoviesDao dao();
    public abstract PopularMoviesDao popularMoviesDao();
    @VisibleForTesting
    public static final String DATABASE_NAME = "NowPlayingMoviesDB";
    private static AppDatabase sInstance;

    //Constructor
    public static AppDatabase getInstance(){
        return sInstance;
    }

    //Constructor which is calling in onCreate method in starting
    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return sInstance;
    }


}
