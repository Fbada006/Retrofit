package com.disruption.retrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.disruption.retrofit.adapter.MoviesAdapter;
import com.disruption.retrofit.model.Movie;
import com.disruption.retrofit.model.OurResponse;
import com.disruption.retrofit.network.MovieApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    private final static String API_KEY = "YOUR_API_KEY";
    private List<Movie> mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getYourMovies();

    }

    //This is the API Call
    private void getYourMovies() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<OurResponse> call = movieApiService.getMovies(API_KEY);
        call.enqueue(new Callback<OurResponse>() {
            @Override
            public void onResponse(@NonNull Call<OurResponse> call, @NonNull Response<OurResponse> response) {
                mMovieList = null;
                if (response.body() != null) {
                    mMovieList = response.body().getResults();
                }
                recyclerView.setAdapter(new MoviesAdapter(mMovieList, R.layout.list_item_movie,
                        MainActivity.this));
            }

            @Override
            public void onFailure(@NonNull Call<OurResponse> call, @NonNull Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
