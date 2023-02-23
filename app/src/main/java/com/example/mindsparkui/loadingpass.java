package com.example.mindsparkui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class loadingpass extends AppCompatActivity {

    LottieAnimationView lottie1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingpass);

        lottie1=findViewById(R.id.lottie1);
        lottie1.setRepeatCount(LottieDrawable.INFINITE);
    }
}