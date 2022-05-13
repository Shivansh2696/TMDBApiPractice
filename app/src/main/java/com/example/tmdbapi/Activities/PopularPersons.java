package com.example.tmdbapi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tmdbapi.Adapters.RecyclerPopularPersonsAdapter;
import com.example.tmdbapi.Networking.MoviesApi;
import com.example.tmdbapi.TMDB.Models.PopularPersons.PopularPersonResultsPojo;
import com.example.tmdbapi.TMDB.Response.PopularPersonResponse;
import com.example.tmdbapi.Networking.RetrofitApi;
import com.example.tmdbapi.databinding.ActivityPopularPersonsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularPersons extends AppCompatActivity {
    ActivityPopularPersonsBinding binding;
    ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    List<PopularPersonResultsPojo> person = new ArrayList<>();
    RecyclerPopularPersonsAdapter adapter;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPopularPersonsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.RecyclerPopularPerson;
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fetchPopularPersons();
    }

    private void fetchPopularPersons() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        MoviesApi apiServices = RetrofitApi.getRetrofit().create(MoviesApi.class);
        Call<PopularPersonResponse> call =apiServices.getPopularPersons("149def2a4a1ee41482f93fb566be0e66");
        call.enqueue(new Callback<PopularPersonResponse>() {
            @Override
            public void onResponse(Call<PopularPersonResponse> call, Response<PopularPersonResponse> response) {
                progressDialog.dismiss();
                person = response.body().getResults();
                adapter = new RecyclerPopularPersonsAdapter(getApplicationContext(),person);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PopularPersonResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PopularPersons.this, "Error : " + t, Toast.LENGTH_SHORT).show();
            }
        });
     }
}