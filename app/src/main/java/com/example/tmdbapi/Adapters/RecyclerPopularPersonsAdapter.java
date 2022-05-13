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
import com.example.tmdbapi.TMDB.Models.PopularPersons.PopularPersonResultsPojo;
import com.example.tmdbapi.TMDB.Response.PopularPersonResponse;
import com.example.tmdbapi.Utils;

import java.util.ArrayList;
import java.util.List;

public class RecyclerPopularPersonsAdapter extends RecyclerView.Adapter<RecyclerPopularPersonsAdapter.MyViewHolder> {

    final private Context context;
    final private List<PopularPersonResultsPojo> person;

    public RecyclerPopularPersonsAdapter(Context context, List<PopularPersonResultsPojo> person) {
        this.context = context;
        this.person = person;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_card_popular_persons,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imageUrl = Utils.IMAGE_BASE_URL + person.get(position).getProfilePath();
        Glide.with(context).load(imageUrl).into(holder.PersonImage);

        holder.Id.setText("ID :" + person.get(position).getId());
        holder.Name.setText(person.get(position).getName());
        holder.Department.setText("Department :" + person.get(position).getDepartment());
        holder.Popularity.setText("Popularity :" + person.get(position).getPopularity());
    }

    @Override
    public int getItemCount() {
            return person.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView PersonImage;
        TextView Name,Id,Department,Popularity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            PersonImage = (ImageView) itemView.findViewById(R.id.PersonImage);
            Name = (TextView) itemView.findViewById(R.id.PersonName);
            Id = (TextView)itemView.findViewById(R.id.PersonID);
            Department = (TextView)itemView.findViewById(R.id.PersonDepartment);
            Popularity =(TextView) itemView.findViewById(R.id.PersonPopularity);
        }
    }
}
