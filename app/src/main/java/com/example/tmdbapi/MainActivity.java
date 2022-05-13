package com.example.tmdbapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmdbapi.Activities.SeeAllActivities.NowPlayingMoviesFullList;
import com.example.tmdbapi.Activities.SeeAllActivities.PopularMoviesFullList;
import com.example.tmdbapi.Adapters.RecyclerNowPlayingMoviesAdapter;
import com.example.tmdbapi.Adapters.RecyclerPopularMoviesAdapter;
import com.example.tmdbapi.RoomDB.DataBase.AppDatabase;
import com.example.tmdbapi.Networking.MoviesApi;
import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;
import com.example.tmdbapi.TMDB.Models.PopularMovies.PopularMoviesResultPojo;
import com.example.tmdbapi.TMDB.Response.NowPlayingMoviesResponse;
import com.example.tmdbapi.TMDB.Response.PopularMoviesResponse;
import com.example.tmdbapi.Networking.RetrofitApi;
import com.example.tmdbapi.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MoviesApi apiServices = RetrofitApi.getRetrofit().create(MoviesApi.class);
    private RecyclerView recyclerViewNowPlayingMovies,recyclerViewPopularMovies;

    List<NowPlayingMoviesResultsPojo> NowPlayingMovies = new ArrayList<>();
    List<PopularMoviesResultPojo> PopularMovies = new ArrayList<>();

    RecyclerNowPlayingMoviesAdapter adapterNowPlayingMovies;
    RecyclerPopularMoviesAdapter adapterPopularMovies;

    LinearLayoutManager layoutManagerNowPlayingMovies,layoutManagerPopularMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerViewNowPlayingMovies = binding.NowPlayingMoviesRecycler;
        recyclerViewPopularMovies = binding.PopularMoviesRecycler;

        layoutManagerNowPlayingMovies = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        layoutManagerPopularMovies = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        recyclerViewNowPlayingMovies.setLayoutManager(layoutManagerNowPlayingMovies);
        recyclerViewNowPlayingMovies.setHasFixedSize(true);
        fetchNowPlayingMovies(1);

        recyclerViewPopularMovies.setLayoutManager(layoutManagerPopularMovies);
        recyclerViewPopularMovies.setHasFixedSize(true);
        fetchPopularMovies(1);

        // DataBase Code  for Now Playing Movies
        AppDatabase.getInstance().dao().getAll().observe(this, new Observer<List<NowPlayingMoviesResultsPojo>>() {
            @Override
            public void onChanged(List<NowPlayingMoviesResultsPojo> nowPlayingMoviesResultsPojos) {
                adapterNowPlayingMovies = new RecyclerNowPlayingMoviesAdapter(MainActivity.this, nowPlayingMoviesResultsPojos);
                recyclerViewNowPlayingMovies.setAdapter(adapterNowPlayingMovies);
            }
        });

        // Database Code for Popular Movies
        AppDatabase.getInstance().popularMoviesDao().getAllPopularMovies().observe(this, new Observer<List<PopularMoviesResultPojo>>() {
            @Override
            public void onChanged(List<PopularMoviesResultPojo> popularMoviesResultPojos) {
                adapterPopularMovies = new RecyclerPopularMoviesAdapter(MainActivity.this, popularMoviesResultPojos);
                recyclerViewPopularMovies.setAdapter(adapterPopularMovies);
            }
        });

        //SeeALL  For Now Playing Movies
        binding.SeeAllNowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NowPlayingMoviesFullList.class);
                startActivity(intent);
            }
        });

        binding.SeeAllPopularMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PopularMoviesFullList.class);
                startActivity(intent);
            }
        });

    }

    // Fetching NowPlaying Movies from API
    private void fetchNowPlayingMovies( int page) {
        Call<NowPlayingMoviesResponse> call = apiServices.getNowPlayingMovies(Utils.KEY,page);
        call.enqueue(new Callback<NowPlayingMoviesResponse>() {
            @Override
            public void onResponse(Call<NowPlayingMoviesResponse> call, Response<NowPlayingMoviesResponse> response) {
                NowPlayingMovies = response.body().getNowPlayingMoviesResult();
                AppDatabase.getInstance().dao().insertNowPlayingMovie(NowPlayingMovies);
            }
            @Override
            public void onFailure(Call<NowPlayingMoviesResponse> call, Throwable t) {

            }
        });
    }

    //Fetching PopularMovies from API
    private void fetchPopularMovies(int page ) {
        Call<PopularMoviesResponse> call = apiServices.getPopularMovies(Utils.KEY,page);
        call.enqueue(new Callback<PopularMoviesResponse>() {
            @Override
            public void onResponse(Call<PopularMoviesResponse> call, Response<PopularMoviesResponse> response) {
                PopularMovies = response.body().getPopularMoviesResult();
                AppDatabase.getInstance().popularMoviesDao().insertPopularMovies(PopularMovies);
            }

            @Override
            public void onFailure(Call<PopularMoviesResponse> call, Throwable t) {

            }
        });
    }

}