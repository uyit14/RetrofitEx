package com.example.uytai.retrofit.rest;

import com.example.uytai.retrofit.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by uytai on 12/4/2017.
 */

public interface ApiInterface {

    //"movie/top_rated" la sau baseURL toi "?"
    //"api_key" la phan sau dau "?"
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
