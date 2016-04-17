package com.michaelfahmy.udacity.moviz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by michael on 4/13/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;
    private OnItemClickListener itemClickListener;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View movieView = inflater.inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Picasso.with(context)
                .load(movie.getPosterPath())
                .error(R.drawable.movie_placeholder)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    /**
     * ViewHolder class
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView moviePoster;

        public ViewHolder(View itemView) {
            super(itemView);
            moviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }


}
