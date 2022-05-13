package com.example.tmdbapi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tmdbapi.R;
import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;
import com.example.tmdbapi.Utils;
import com.example.tmdbapi.databinding.ActivityMoviesDetailsBinding;

public class NowPlayingMoviesDetails extends AppCompatActivity {
    ActivityMoviesDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NowPlayingMoviesResultsPojo NowPlayingMovies = (NowPlayingMoviesResultsPojo) getIntent().getExtras().getSerializable("MovieDetail");

        binding.MovieName.setText( NowPlayingMovies.getOriginalTitle());
        binding.MovieLanguage.setText(NowPlayingMovies.getOriginalLanguage());
        binding.MoviePopularity.setText("" +NowPlayingMovies.getPopularity());
        binding.MovieReleaseDate.setText(NowPlayingMovies.getReleaseDate());
        binding.MovieOverView.setText(NowPlayingMovies.getOverView());
        binding.MovieRating.setText(""+ NowPlayingMovies.getVoteAverage());
        binding.MovieVoteCount.setText(""+ NowPlayingMovies.getVoteCount());

        String Image = Utils.IMAGE_BASE_URL + NowPlayingMovies.getPosterPath();
        String BackgroundImage = Utils.IMAGE_BASE_URL + NowPlayingMovies.getBackdropPath();

        Glide.with(this).load(Image).into(binding.MovieImage);
        Glide.with(this).load(BackgroundImage).into(binding.MovieBackGroundImage);
    }
}