package com.michaelfahmy.udacity.moviz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by michael on 4/13/16.
 */
public class FetchMovies {

    private static final String TAG = "FetchMovies";
    private static final String BASE_URL = "http://api.themoviedb.org";
    private static final String PREF_ORDER_KEY = "order_preference";
    private static final String POPULAR_MOVIES = "popular";
    private static final String TOP_RATED_MOVIES = "top_rated";
    private static final String UPCOMING_MOVIES = "upcoming";

    public static Movies moviesResponse;
    public static List<Movie> movies;
    Activity activity;
    View activityView;
    int page;
    String order;
    MovieApi movieApi;
    Call<Movies> call;

    public FetchMovies(Activity activity, View view, int page) {
        this.activity = activity;
        this.activityView = view;
        this.page = page;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        this.order = sharedPreferences.getString(PREF_ORDER_KEY, POPULAR_MOVIES);
        movies = new ArrayList<>();

        initConfig();
    }

    private void initConfig() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        movieApi = retrofit.create(MovieApi.class);

        call = movieApi.fetchMovies(order, BuildConfig.API_KEY, page);
    }

    public void connectAndFetch() {
        call.clone().enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.d(TAG, order + ": " + response.code() + " - " + response.message());
                moviesResponse = response.body();
                movies.clear();
                movies.addAll(moviesResponse.results);
                Log.d(TAG, movies.get(0).getTitle());
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                Snackbar.make(activityView, "No connection", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                connectAndFetch();
                            }
                        })
                        .setActionTextColor(ContextCompat.getColor(activity, R.color.colorAccent))
                        .show();
            }
        });
    }

    public void refresh() {
        call.clone().enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.d(TAG, order + " ~ " + response.code() + " - " + response.message());
                moviesResponse = response.body();
                movies.clear();
                movies.addAll(moviesResponse.results);
                Log.d(TAG, movies.get(0).getTitle());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                Snackbar.make(activityView, "Connection Failed", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                refresh();
                            }
                        })
                        .setActionTextColor(ContextCompat.getColor(activity, R.color.colorAccent))
                        .show();
            }
        });
    }

    @SuppressWarnings("unused")
    public void loadMore(final int page) {
        call = movieApi.fetchMovies(order, BuildConfig.API_KEY, page);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.d(TAG, order + ": " + response.code() + " - " + response.message());
                moviesResponse = response.body();
                movies.addAll(moviesResponse.results);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                Snackbar.make(activityView, "Connection Failed", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadMore(page);
                            }
                        })
                        .setActionTextColor(ContextCompat.getColor(activity, R.color.colorAccent))
                        .show();
            }
        });
    }

}
