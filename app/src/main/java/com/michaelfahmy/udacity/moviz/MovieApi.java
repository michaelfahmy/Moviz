package com.michaelfahmy.udacity.moviz;


import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by michael on 4/9/16.
 */
public interface MovieApi {
    // order maybe 'popular', 'top_rated', 'upcoming'
    @GET("/3/movie/{order}")
    Call<Movies> fetchMovies(@Path("order") String order, @Query("api_key") String key, @Query("page") int page);
}