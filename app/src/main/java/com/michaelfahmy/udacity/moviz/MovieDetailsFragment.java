package com.michaelfahmy.udacity.moviz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);

        Intent intent = getActivity().getIntent();
        Movie movie = FetchMovies.movies.get(intent.getIntExtra("position", 1));

        TextView title = (TextView) rootView.findViewById(R.id.title);
        AppCompatImageView poster = (AppCompatImageView) rootView.findViewById(R.id.poster);

        title.setText(movie.getTitle());
        Picasso.with(getContext()).load(movie.getPosterPath()).into(poster);

        return rootView;
    }

}
