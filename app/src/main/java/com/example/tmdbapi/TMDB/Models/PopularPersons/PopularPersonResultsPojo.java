package com.example.tmdbapi.TMDB.Models.PopularPersons;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularPersonResultsPojo {

    @SerializedName("id")
    private int id;

    @SerializedName("known_for")
    private List<PopularPersonKnownForPojo> knownFor;

    @SerializedName("known_for_department")
    private String Department;

    @SerializedName("name")
    private String Name;

    @SerializedName("popularity")
    private String Popularity;

    @SerializedName("profile_path")
    private String ProfilePath;


    //Getters
    public int getId() {
        return id;
    }

    public List<PopularPersonKnownForPojo> getKnownFor() {
        return knownFor;
    }

    public String getDepartment() {
        return Department;
    }

    public String getName() {
        return Name;
    }

    public String getPopularity() {
        return Popularity;
    }

    public String getProfilePath() {
        return ProfilePath;
    }

    @Override
    public String toString() {
        return "PopularPersonResultsPojo{" +
                "id=" + id +
                ", knownFor=" + knownFor +
                ", Department='" + Department + '\'' +
                ", Name='" + Name + '\'' +
                ", Popularity='" + Popularity + '\'' +
                ", ProfilePath='" + ProfilePath + '\'' +
                '}';
    }
}
