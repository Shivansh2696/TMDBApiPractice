package com.example.tmdbapi.Networking;


import com.example.tmdbapi.TMDB.Response.NowPlayingMoviesResponse;
import com.example.tmdbapi.TMDB.Response.PopularMoviesResponse;
import com.example.tmdbapi.TMDB.Response.PopularPersonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET("/3/person/popular")
    Call<PopularPersonResponse> getPopularPersons(@Query("api_key") String apiKey);

    @GET("/3/movie/now_playing")
    Call<NowPlayingMoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("/3/movie/popular")
    Call<PopularMoviesResponse>  getPopularMovies(@Query("api_key") String apiKey, @Query("page") Integer page);

}
