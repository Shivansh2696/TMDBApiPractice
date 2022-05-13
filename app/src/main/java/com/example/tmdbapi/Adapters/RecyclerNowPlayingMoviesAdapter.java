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

import com.bumptech.glide.Glide;
import com.example.tmdbapi.Activities.NowPlayingMoviesDetails;
import com.example.tmdbapi.R;
import com.example.tmdbapi.TMDB.Models.NowPlayingMovies.NowPlayingMoviesResultsPojo;
import com.example.tmdbapi.Utils;

import java.util.List;

public class RecyclerNowPlayingMoviesAdapter extends RecyclerView.Adapter<RecyclerNowPlayingMoviesAdapter.MyViewHolder> {
    private final Context context;
    private final List<NowPlayingMoviesResultsPojo> movies;


    public RecyclerNowPlayingMoviesAdapter(Context context, List<NowPlayingMoviesResultsPojo> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerNowPlayingMoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_card_now_playing_movies,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerNowPlayingMoviesAdapter.MyViewHolder holder, int position) {
        holder.bind(movies.get(position));

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        TextView Rating, MovieName,ReleaseDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ImageNowPlayingMovies);
            Rating = itemView.findViewById(R.id.RatingNowPlayingMovies);
            MovieName = itemView.findViewById(R.id.NameNowPlayingMovies);
            ReleaseDate = itemView.findViewById(R.id.ReleaseDateNowPlayingMovies);
            cardView = itemView.findViewById(R.id.NowPlayingMoviesCard);
        }


        private void bind(NowPlayingMoviesResultsPojo movies){
            String image = Utils.IMAGE_BASE_URL + movies.getPosterPath();
            Glide.with(context).load(image).into(imageView);
            Rating.setText(""+ movies.getVoteAverage());
            MovieName.setText(movies.getOriginalTitle());
            ReleaseDate.setText(movies.getReleaseDate());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NowPlayingMoviesDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MovieDetail",movies);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
