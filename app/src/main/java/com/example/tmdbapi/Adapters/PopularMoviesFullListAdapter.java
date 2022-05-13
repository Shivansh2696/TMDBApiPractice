package com.example.tmdbapi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdbapi.R;
import com.example.tmdbapi.TMDB.Models.PopularMovies.PopularMoviesResultPojo;
import com.example.tmdbapi.Utils;

import java.util.List;

public class PopularMoviesFullListAdapter extends RecyclerView.Adapter<PopularMoviesFullListAdapter.MyViewHolder> {

    private final Context context;
    private  List<PopularMoviesResultPojo>  Movies;

    public PopularMoviesFullListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PopularMoviesFullListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.now_playing_movies_full_detail_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMoviesFullListAdapter.MyViewHolder holder, int position) {
        holder.bind(Movies.get(position));
    }

    @Override
    public int getItemCount() {
        if(Movies == null) return 0;
        return Movies.size();
    }

    public void setMovies(List<PopularMoviesResultPojo> movies) {
        Movies = movies;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView MovieName,Rating,ReleaseDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.PersonImage);
            MovieName = itemView.findViewById(R.id.MovieName);
            Rating = itemView.findViewById(R.id.MovieRating);
            ReleaseDate = itemView.findViewById(R.id.ReleaseDate);
        }

        public void bind(PopularMoviesResultPojo Movies) {
            String image = Utils.IMAGE_BASE_URL + Movies.getPosterPath();
            Glide.with(context).load(image).into(imageView);

            MovieName.setText(Movies.getOriginalTitle());
            Rating.setText("" + Movies.getVoteAverage());
            ReleaseDate.setText(Movies.getReleaseDate());
        }
    }
}
