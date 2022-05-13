package com.example.tmdbapi.TMDB.Response;

import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingMoviesResponse {

    @SerializedName("page")
    private int NowPlayingMoviesPage;

    @SerializedName("results")
    private List<NowPlayingMoviesResultsPojo> NowPlayingMoviesResult;

    public int getNowPlayingMoviesPage() {
        return NowPlayingMoviesPage;
    }

    public void setNowPlayingMoviesPage(int nowPlayingMoviesPage) {
        NowPlayingMoviesPage = nowPlayingMoviesPage;
    }

    public List<NowPlayingMoviesResultsPojo> getNowPlayingMoviesResult() {
        return NowPlayingMoviesResult;
    }

    public void setNowPlayingMoviesResult(List<NowPlayingMoviesResultsPojo> nowPlayingMoviesResult) {
        NowPlayingMoviesResult = nowPlayingMoviesResult;
    }
}
