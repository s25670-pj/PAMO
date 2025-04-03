package com.example.kalkulatorbmi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 3000;
    private boolean isStartButtonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStartButtonClicked = true;
                openMainMenu();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isStartButtonClicked) {
                    openMainMenu();
                }
            }
        }, SPLASH_DELAY);
    }

    private void openMainMenu() {
        Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}