package com.example.tmdbapi.RoomDB.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.tmdbapi.TMDB.Models.PopularMovies.PopularMoviesResultPojo;

import java.util.List;

@Dao
public interface PopularMoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPopularMovies(List<PopularMoviesResultPojo> Movie);

    @Query("select * from PopularMovies")
    LiveData<List<PopularMoviesResultPojo>> getAllPopularMovies();
}
