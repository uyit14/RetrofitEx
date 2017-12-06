package com.example.uytai.retrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.uytai.retrofit.R;
import com.example.uytai.retrofit.activity.DetailActivity;
import com.example.uytai.retrofit.activity.MainActivity;
import com.example.uytai.retrofit.model.Movie;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by uytai on 12/4/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    static List<Movie> movies;
    private int rowLayout;
    static Context context;

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());

        String date_str = movies.get(position).getReleaseDate();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(date_str);
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            String finalString = newFormat.format(date);
            holder.data.setText(finalString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    public class MovieViewHolder extends RecyclerView.ViewHolder{
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);

            //set onClick in RycycerView
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Title", movies.get(getPosition()).getTitle());
                    bundle.putString("Description", movies.get(getPosition()).getOverview());
                    bundle.putString("Poster", movies.get(getPosition()).getPosterPath());
                    bundle.putDouble("Rating", movies.get(getPosition()).getVoteAverage());
                    bundle.putString("Date", movies.get(getPosition()).getReleaseDate());
                    //bundle.putInt("Id", movies.get(getPosition()).getId());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }

    }


}
