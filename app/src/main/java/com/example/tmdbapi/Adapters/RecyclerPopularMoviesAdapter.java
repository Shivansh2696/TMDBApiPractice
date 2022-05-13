package com.example.tmdbapi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdbapi.Activities.PopularMoviesDetails;
import com.example.tmdbapi.R;
import com.example.tmdbapi.TMDB.Models.PopularMovies.PopularMoviesResultPojo;
import com.example.tmdbapi.Utils;

import java.io.Serializable;
import java.util.List;

public class RecyclerPopularMoviesAdapter extends RecyclerView.Adapter<RecyclerPopularMoviesAdapter.MyViewHolder>{
    private final Context context;
    private final List<PopularMoviesResultPojo> Popularmovies;

    public RecyclerPopularMoviesAdapter(Context context, List<PopularMoviesResultPojo> Popularmovies) {
        this.context = context;
        this.Popularmovies = Popularmovies;
    }

    @NonNull
        @Override
        public RecyclerPopularMoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_card_popular_movies,parent,false);
        return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerPopularMoviesAdapter.MyViewHolder holder, int position) {
            holder.bind(Popularmovies.get(position));



        }

        @Override
        public int getItemCount() {
            return Popularmovies.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            CardView cardView;
            TextView Rating, MovieName,ReleaseDate;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                 imageView = itemView.findViewById(R.id.ImagePopularMovies);
                 Rating = itemView.findViewById(R.id.RatingPopularMovies);
                 MovieName = itemView.findViewById(R.id.NamePopularMovies);
                 ReleaseDate = itemView.findViewById(R.id.ReleaseDatePopularMovies);
                 cardView = itemView.findViewById(R.id.PopularMoviesCard);

            }

            public void bind(PopularMoviesResultPojo movies) {
                String image = Utils.IMAGE_BASE_URL + movies.getPosterPath();
                Glide.with(context).load(image).into(imageView);

                Rating.setText("" + movies.getVoteAverage());
                MovieName.setText(movies.getOriginalTitle());
                ReleaseDate.setText(movies.getReleaseDate());

                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PopularMoviesDetails.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("PopularMoviesDetails", movies);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }
