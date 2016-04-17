package com.michaelfahmy.udacity.moviz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View root = findViewById(R.id.splash_view);
        new FetchMovies(this, root, 1).connectAndFetch();

    }

}
