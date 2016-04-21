package com.example.francisco.tfsservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            Thread.sleep(5000);
            this.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
