package com.example.tmdbapi.TMDB.Response;

import com.example.tmdbapi.TMDB.Models.PopularPersons.PopularPersonResultsPojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularPersonResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<PopularPersonResultsPojo> results;

    //Getters

    public int getPage() {
        return page;
    }

    public List<PopularPersonResultsPojo> getResults() {
        return results;
    }
}
