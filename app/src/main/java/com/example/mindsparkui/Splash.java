package com.example.mindsparkui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash extends AppCompatActivity {
    TextView appname;
    LottieAnimationView lottie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appname=findViewById(R.id.appname);
        lottie=findViewById(R.id.lottie);
        appname.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottie.animate().setDuration(4000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        },4000);
    }
}