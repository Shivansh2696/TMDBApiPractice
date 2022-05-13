package com.example.tmdbapi.Tasks;

import com.example.tmdbapi.Networking.Network;
import com.example.tmdbapi.RoomDB.DataBase.AppDatabase;
import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;
import com.example.tmdbapi.TMDB.Response.NowPlayingMoviesResponse;
import com.example.tmdbapi.Utils;

import java.io.IOException;
import java.util.List;


public class NowPlayingMoviesRunner implements Runnable{
    private int page;
    private NowPlayingMoviesResult result;

    public NowPlayingMoviesRunner(int page, NowPlayingMoviesResult result) {
        this.page = page;
        this.result = result;
    }

    @Override
    public void run() {
        try {
            NowPlayingMoviesResponse model = Network.getInstance().getMoviesApi().getNowPlayingMovies(Utils.KEY,page).execute().body();
            List<NowPlayingMoviesResultsPojo> nowPlayingMoviesResult = model.getNowPlayingMoviesResult();
            AppDatabase.getInstance().dao().insertNowPlayingMovie(nowPlayingMoviesResult);
            result.OnSuccess();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public interface NowPlayingMoviesResult{
        void OnSuccess();
    }
}
