package com.example.khudanibaron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.jaeger.library.StatusBarUtil;

public class MenuSplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_menu_splash);

        StatusBarUtil.setTransparent(this);

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

    public void doWork() {
        progressBar.setVisibility(View.VISIBLE);
        for (progress=25; progress<=100; progress+=25)
        {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startApp() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}