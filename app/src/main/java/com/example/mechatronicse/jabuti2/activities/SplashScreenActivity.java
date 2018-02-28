package com.example.mechatronicse.jabuti2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mechatronicse.jabuti2.R;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

     final    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Thread myThread = new Thread() {
            @Override
            public void run() {

                try {

                    sleep(3000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {
                    startActivity(intent);
                    finish();
                }


            }

        };
    myThread.start();
    }

}


