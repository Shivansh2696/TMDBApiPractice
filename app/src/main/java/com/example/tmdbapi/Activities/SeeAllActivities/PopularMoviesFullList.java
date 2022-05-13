package com.example.tmdbapi.Activities.SeeAllActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tmdbapi.Adapters.PopularMoviesFullListAdapter;
import com.example.tmdbapi.RoomDB.DataBase.AppDatabase;
import com.example.tmdbapi.Networking.MoviesApi;
import com.example.tmdbapi.TMDB.Models.PopularMovies.PopularMoviesResultPojo;
import com.example.tmdbapi.TMDB.Response.PopularMoviesResponse;
import com.example.tmdbapi.Networking.RetrofitApi;
import com.example.tmdbapi.Utils;
import com.example.tmdbapi.databinding.ActivityPopularMoviesFullDetailsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMoviesFullList extends AppCompatActivity {
    ActivityPopularMoviesFullDetailsBinding binding;
    private RecyclerView recyclerView;
    List<PopularMoviesResultPojo> PopularMovies = new ArrayList<>();
    MoviesApi apiServices = RetrofitApi.getRetrofit().create(MoviesApi.class);
    PopularMoviesFullListAdapter adapter;
    LinearLayoutManager layoutManager;
    int page = 0;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPopularMoviesFullDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.PopularMoviesFullDetailRecycler;
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new PopularMoviesFullListAdapter(PopularMoviesFullList.this);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            fetchMovies();
                            loading = true;
                        }
                    }
                }
            }
        });

        fetchMovies();

        AppDatabase.getInstance().popularMoviesDao().getAllPopularMovies().observe(this, new Observer<List<PopularMoviesResultPojo>>() {
            @Override
            public void onChanged(List<PopularMoviesResultPojo> popularMoviesResultPojos) {
                adapter.setMovies(popularMoviesResultPojos);
            }
        });

    }

    private void fetchMovies() {
        page = page+1;
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