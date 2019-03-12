package com.disruption.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OurResponse {
    @SerializedName("results")
    private List<Movie> results = new ArrayList<>();

    public List<Movie> getResults() {
        return results;
    }
}
