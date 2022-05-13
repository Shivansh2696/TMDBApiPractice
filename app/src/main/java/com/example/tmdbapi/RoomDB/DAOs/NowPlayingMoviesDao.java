package com.example.tmdbapi.RoomDB.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;

import java.util.List;


@Dao
public interface NowPlayingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNowPlayingMovie(List<NowPlayingMoviesResultsPojo> Movie);

    @Query("select * from NowPlayingMovies")
    LiveData<List<NowPlayingMoviesResultsPojo>> getAll();



}
