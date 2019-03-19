package com.disruption.retrofit.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.disruption.retrofit.R;
import com.disruption.retrofit.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private final List<Movie> mMovieList;
    private final int rowLayout;
    private final Context context;
    private static final String IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w342//";

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.mMovieList = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(context).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        String image_url = IMAGE_URL_BASE_PATH + mMovieList.get(position).getPosterPath();

        //Make sure you have  valid URL to avoid some small errors
        if (image_url.length() != 0) {
            Picasso.get().load(image_url).fit().centerCrop()
                    .placeholder(R.drawable.ic_error_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder.movieImage);
        } else {
            holder.movieImage.setImageResource(R.drawable.ic_error_black_24dp);
        }

        holder.movieTitle.setText(mMovieList.get(position).getTitle());
        holder.data.setText(mMovieList.get(position).getReleaseDate());
        holder.movieDescription.setText(mMovieList.get(position).getOverview());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout moviesLayout;
        final TextView movieTitle;
        final TextView data;
        final TextView movieDescription;
        final ImageView movieImage;

        MovieViewHolder(View v) {
            super(v);
            moviesLayout = v.findViewById(R.id.movies_layout);
            movieImage = v.findViewById(R.id.movie_image);
            movieTitle = v.findViewById(R.id.title);
            data = v.findViewById(R.id.date);
            movieDescription = v.findViewById(R.id.description);
        }

    }
}