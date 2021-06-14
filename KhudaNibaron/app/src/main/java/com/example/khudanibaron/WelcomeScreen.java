package com.example.khudanibaron;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class WelcomeScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progress;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome_screen);

        getWindow().setStatusBarColor(ContextCompat.getColor(WelcomeScreen.this, R.color.yellow));

        progressBar = findViewById(R.id.progressBarId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();

                startApp();
            }
        });

        thread.start();
    }

    public void doWork(){
        progressBar.setVisibility(View.VISIBLE);
        for (progress=20; progress<=100; progress+=20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startApp(){
        Intent intent = new Intent(WelcomeScreen.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}