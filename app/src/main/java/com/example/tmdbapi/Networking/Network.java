package com.example.tmdbapi.Networking;

import com.example.tmdbapi.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Network instance;
    private OkHttpClient.Builder httpClient;
    private MoviesApi moviesApi;

    public static Network getInstance() {
        if (instance==null)
            instance=new Network();
        return instance;
    }

    private Network(){
        httpClient = new OkHttpClient.Builder();
       moviesApi = create(Utils.BASE_URL,MoviesApi.class);
    }

    public MoviesApi getMoviesApi() {
        return moviesApi;
    }

    private <T> T create(String baseUrl, Class<T> cls){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        T t=retrofit.create(cls);
        return t;
    }
}
