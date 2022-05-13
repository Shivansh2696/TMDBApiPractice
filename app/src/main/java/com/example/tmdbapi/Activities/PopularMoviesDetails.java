package com.example.tmdbapi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tmdbapi.R;
import com.example.tmdbapi.TMDB.Models.PopularMovies.PopularMoviesResultPojo;
import com.example.tmdbapi.Utils;
import com.example.tmdbapi.databinding.ActivityPopularMoviesDetailsBinding;
import com.example.tmdbapi.databinding.ActivityPopularPersonsBinding;

public class PopularMoviesDetails extends AppCompatActivity {
    ActivityPopularMoviesDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPopularMoviesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PopularMoviesResultPojo popularMovies = (PopularMoviesResultPojo) getIntent().getExtras().getSerializable("PopularMoviesDetails");

        binding.PopularMovieName.setText( popularMovies.getOriginalTitle());
        binding.PopularMovieLanguage.setText(popularMovies.getOriginalLanguage());
        binding.PopularMoviePopularity.setText("" +popularMovies.getPopularity());
        binding.PopularMovieReleaseDate.setText(popularMovies.getReleaseDate());
        binding.PopularMovieOverView.setText(popularMovies.getOverView());
        binding.PopularMovieRating.setText(""+ popularMovies.getVoteAverage());
        binding.PopularMovieVoteCount.setText(""+ popularMovies.getVoteCount());

        String Image = Utils.IMAGE_BASE_URL + popularMovies.getPosterPath();
        String BackgroundImage = Utils.IMAGE_BASE_URL + popularMovies.getBackDropPath();

        Glide.with(this).load(Image).into(binding.PopularMovieImage);
        Glide.with(this).load(BackgroundImage).into(binding.PopularMovieBackGroundImage);
    }
}