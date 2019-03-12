package com.disruption.retrofit.network;

import com.disruption.retrofit.model.OurResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/top_rated")
    Call<OurResponse> getMovies(@Query("api_key") String apiKey);
}