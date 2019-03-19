package com.disruption.retrofit.viewModel;

import android.content.Context;
import android.util.Log;

import com.disruption.retrofit.BuildConfig;
import com.disruption.retrofit.MainActivity;
import com.disruption.retrofit.model.Movie;
import com.disruption.retrofit.model.OurResponse;
import com.disruption.retrofit.network.MovieApiService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewModel extends ViewModel {
    private static final String TAG = MovieViewModel.class.getSimpleName();
    private static Retrofit retrofit = null;
    private MutableLiveData<List<Movie>> mMovieList;


    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public LiveData<List<Movie>> getMovies(Context context) {
        if (mMovieList == null) {
            mMovieList = new MutableLiveData<>();

            getMovieList(context);
        }

        return mMovieList;
    }

    //This is the API Call
    private void getMovieList(final Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        String api_key = BuildConfig.BingerSecretKey;
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<OurResponse> call = movieApiService.getMovies(api_key);
        call.enqueue(new Callback<OurResponse>() {
            @Override
            public void onResponse(@NonNull Call<OurResponse> call, @NonNull Response<OurResponse> response) {
                if (response.body() != null) {
                    Log.e(TAG, "---------------------------------------------------Actively getting article data! " );
                    mMovieList.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<OurResponse> call, @NonNull Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

}
