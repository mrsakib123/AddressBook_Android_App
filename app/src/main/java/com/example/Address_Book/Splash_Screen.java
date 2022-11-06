package com.example.Address_Book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Splash_Screen extends AppCompatActivity {


    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Splash Screen");


        setContentView(R.layout.activity_splash_screen);



        progressBar = (ProgressBar) findViewById(R.id.progressBarId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dowork();
                stratApp();
            }
        });
        thread.start();
    }

    public void dowork() {

        for (progress = 20; progress < 100; progress = progress + 20) {

            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stratApp(){

        Intent intent =new Intent(Splash_Screen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}