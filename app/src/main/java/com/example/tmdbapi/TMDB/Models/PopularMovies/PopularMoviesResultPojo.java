package com.example.tmdbapi.TMDB.Models.PopularMovies;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "PopularMovies")
public class PopularMoviesResultPojo implements Serializable {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("backdrop_path")
    private String BackDropPath;  //BackgroundPoster

    @SerializedName("original_language")
    private String OriginalLanguage;

    @SerializedName("original_title")
    private String OriginalTitle;

    @SerializedName("overview")
    private String OverView;

    @SerializedName("popularity")
    private double Popularity;

    @SerializedName("poster_path")
    private String PosterPath;         //PosterImage

    @SerializedName("release_date")
    private String ReleaseDate;

    @SerializedName("vote_average")
    private double VoteAverage;   //Rating

    @SerializedName("vote_count")
    private int VoteCount;


    //Getters
    public String getBackDropPath() {
        return BackDropPath;
    }

    public String getOriginalLanguage() {
        return OriginalLanguage;
    }

    public String getOriginalTitle() {
        return OriginalTitle;
    }

    public String getOverView() {
        return OverView;
    }

    public double getPopularity() {
        return Popularity;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public double getVoteAverage() {
        return VoteAverage;
    }

    public int getVoteCount() {
        return VoteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBackDropPath(String backDropPath) {
        BackDropPath = backDropPath;
    }

    public void setOriginalLanguage(String originalLanguage) {
        OriginalLanguage = originalLanguage;
    }

    public void setOriginalTitle(String originalTitle) {
        OriginalTitle = originalTitle;
    }

    public void setOverView(String overView) {
        OverView = overView;
    }

    public void setPopularity(double popularity) {
        Popularity = popularity;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        VoteAverage = voteAverage;
    }

    public void setVoteCount(int voteCount) {
        VoteCount = voteCount;
    }

    @Override
    public String toString() {
        return "PopularMoviesResultPojo{" +
                "id=" + id +
                ", BackDropPath='" + BackDropPath + '\'' +
                ", OriginalLanguage='" + OriginalLanguage + '\'' +
                ", OriginalTitle='" + OriginalTitle + '\'' +
                ", OverView='" + OverView + '\'' +
                ", Popularity=" + Popularity +
                ", PosterPath='" + PosterPath + '\'' +
                ", ReleaseDate='" + ReleaseDate + '\'' +
                ", VoteAverage=" + VoteAverage +
                ", VoteCount=" + VoteCount +
                '}';
    }
}
