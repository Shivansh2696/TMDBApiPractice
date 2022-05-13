package com.example.tmdbapi.Activities.SeeAllActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tmdbapi.Adapters.NowPlayingMoviesFullListAdapter;
import com.example.tmdbapi.RoomDB.DataBase.AppDatabase;
import com.example.tmdbapi.Networking.MoviesApi;
import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;
import com.example.tmdbapi.TMDB.Response.NowPlayingMoviesResponse;
import com.example.tmdbapi.Networking.RetrofitApi;
import com.example.tmdbapi.Tasks.NowPlayingMoviesRunner;
import com.example.tmdbapi.Utils;
import com.example.tmdbapi.databinding.ActivityNowPlayingMoviesFullListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingMoviesFullList extends AppCompatActivity {
    ActivityNowPlayingMoviesFullListBinding binding;
    private RecyclerView recyclerView;
    List<NowPlayingMoviesResultsPojo> NowPlayingMovies = new ArrayList<>();
    MoviesApi apiServices = RetrofitApi.getRetrofit().create(MoviesApi.class);
    NowPlayingMoviesFullListAdapter adapter;
    LinearLayoutManager layoutManager;
    int page = 0;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNowPlayingMoviesFullListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.RecyclerFullDetailNowPlayingMovies;
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new NowPlayingMoviesFullListAdapter(NowPlayingMoviesFullList.this);
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
                        }
                    }
                }
            }
        });
        fetchMovies();

        AppDatabase.getInstance().dao().getAll().observe(this, new Observer<List<NowPlayingMoviesResultsPojo>>() {
            @Override
            public void onChanged(List<NowPlayingMoviesResultsPojo> nowPlayingMoviesResultsPojos) {
                adapter.setMovies(nowPlayingMoviesResultsPojos);
            }
        });
    }

    private void fetchMovies() {
        page = page+1;
        new Thread(new NowPlayingMoviesRunner(page, new NowPlayingMoviesRunner.NowPlayingMoviesResult() {
            @Override
            public void OnSuccess() {
                loading = true;
            }
        }));
    }

}