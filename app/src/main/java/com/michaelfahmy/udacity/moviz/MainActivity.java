package com.michaelfahmy.udacity.moviz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MoviesAdapter adapter;
    RecyclerView recyclerView;
    FetchMovies fetchMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new MoviesAdapter(this, FetchMovies.movies);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("Clicked", position + "");
                startActivity(
                        new Intent(MainActivity.this, MovieDetailsActivity.class)
                                .putExtra("position", position));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        // TODO: add zoom option in toolbar and change coloumn width 250, 300, 400, 500
        recyclerView.setLayoutManager(new GridAutofitLayoutManager(this, 500));
        recyclerView.setAdapter(adapter);

        fetchMovies = new FetchMovies(this, findViewById(R.id.main_view), 1);
    }

    private void updateMovies() {
        fetchMovies.refresh();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMovies();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh) {
            updateMovies();
            return true;
        } else if (id == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
