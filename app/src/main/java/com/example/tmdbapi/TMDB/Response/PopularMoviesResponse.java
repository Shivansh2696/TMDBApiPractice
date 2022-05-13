package com.example.tmdbapi.TMDB.Response;

import com.example.tmdbapi.TMDB.Models.PopularMovies.PopularMoviesResultPojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMoviesResponse {

    @SerializedName("page")
    private int PopularMoviesPage;

    @SerializedName("results")
    private List<PopularMoviesResultPojo>  PopularMoviesResult;

    @SerializedName("total_pages")
    private int PopularMoviesTotalPages;

    @SerializedName("total_results")
    private int PopularMoviesTotalResults;

    //Getters


    public int getPopularMoviesPage() {
        return PopularMoviesPage;
    }

    public List<PopularMoviesResultPojo> getPopularMoviesResult() {
        return PopularMoviesResult;
    }

    public int getPopularMoviesTotalPages() {
        return PopularMoviesTotalPages;
    }

    public int getPopularMoviesTotalResults() {
        return PopularMoviesTotalResults;
    }
}
