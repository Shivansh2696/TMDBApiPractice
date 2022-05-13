package com.example.tmdbapi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import com.bumptech.glide.Glide;
import com.example.tmdbapi.Activities.NowPlayingMoviesDetails;
import com.example.tmdbapi.R;
import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;
import com.example.tmdbapi.Utils;

import java.util.List;

public class NowPlayingMoviesFullListAdapter extends RecyclerView.Adapter<NowPlayingMoviesFullListAdapter.MyViewHolder> {
    private final Context context;
    private  List<NowPlayingMoviesResultsPojo> movies;

    public NowPlayingMoviesFullListAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public NowPlayingMoviesFullListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.now_playing_movies_full_detail_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingMoviesFullListAdapter.MyViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        if(movies == null) return 0;
        return movies.size();
    }

    public void setMovies(List<NowPlayingMoviesResultsPojo> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView MovieName,Rating,ReleaseDate;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.PersonImage);
            MovieName = itemView.findViewById(R.id.MovieName);
            Rating = itemView.findViewById(R.id.MovieRating);
            ReleaseDate = itemView.findViewById(R.id.ReleaseDate);
            cardView= itemView.findViewById(R.id.cardView);
        }

        public void bind(NowPlayingMoviesResultsPojo Movies) {
            String image = Utils.IMAGE_BASE_URL + Movies.getPosterPath();
            Glide.with(context).load(image).into(imageView);

            MovieName.setText(Movies.getOriginalTitle());
            Rating.setText("" + Movies.getVoteAverage());
            ReleaseDate.setText(Movies.getReleaseDate());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NowPlayingMoviesDetails.class);
                    Bundle bundle  = new Bundle();
                    bundle.putSerializable("MovieDetail", Movies);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
