package com.disruption.retrofit.view.ui;

import android.os.Bundle;

import com.disruption.retrofit.R;
import com.disruption.retrofit.service.model.Movie;
import com.disruption.retrofit.view.adapter.MoviesAdapter;
import com.disruption.retrofit.viewModel.MovieViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MovieViewModel.class.getSimpleName();
    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesAdapter = new MoviesAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(mMoviesAdapter);

        observeViewModelForMovies();
    }

    private void observeViewModelForMovies() {
        MovieViewModel movieViewModel =
                ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getMovieListObservable().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null) {
                    mMoviesAdapter.setMovieList(movies);
                    mMoviesAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
