package com.example.mindsparkui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class loadingpass extends AppCompatActivity {

    private LottieAnimationView lottie1;
    private JSONObject apiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingpass);

        lottie1 = findViewById(R.id.lottie1);
        lottie1.setRepeatCount(LottieDrawable.INFINITE);

        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");

        // Start API call using email and password
        startApiCall(email, password);
    }

    private void startApiCall(String email, String password) {
        // Create JSON object containing email and password
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("email", email);
            requestBody.put("password", password);
        } catch (JSONException e) {
            // Handle JSON error
            e.printStackTrace();
            return;
        }



}}