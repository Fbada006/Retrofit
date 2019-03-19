package com.disruption.retrofit;

import android.os.Bundle;
import android.util.Log;

import com.disruption.retrofit.adapter.MoviesAdapter;
import com.disruption.retrofit.model.Movie;
import com.disruption.retrofit.viewModel.MovieViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Get the data from the ViewModel
        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getMovies(this).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, MainActivity.this));
            }
        });

    }
}
