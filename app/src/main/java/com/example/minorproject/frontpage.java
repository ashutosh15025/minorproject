
package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class frontpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //start your activity here
            }

        }, 100000L);
        SharedPreferences sharedPref = getSharedPreferences("IDvalue", 0);
        if(sharedPref.contains("email")){
            new Timer().schedule(new TimerTask(){
                public void run() {
                    frontpage.this.runOnUiThread(new Runnable() {
                        public void run() {
                            startActivity(new Intent(frontpage.this, Recordactivity.class));
                            finish();
                        }
                    });
                }
            }, 2000);

    }
        else
        {
            new Timer().schedule(new TimerTask(){
                public void run() {
                    frontpage.this.runOnUiThread(new Runnable() {
                        public void run() {
                            startActivity(new Intent(frontpage.this, firstpage.class));
                            finish();
                        }
                    });
                }
            }, 2000);
        }
}}