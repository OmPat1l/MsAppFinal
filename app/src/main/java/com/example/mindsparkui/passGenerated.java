package com.example.mindsparkui;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mindsparkui.databinding.ActivityPassGeneratedBinding;

//public class passGenerated extends AppCompatActivity {
//
//    private AppBarConfiguration appBarConfiguration;
//    private ActivityPassGeneratedBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityPassGeneratedBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//
//    }
//
//
//}
public class passGenerated extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPassGeneratedBinding binding;

    private TextView messageTextView;
    private ImageView imageView;
    private TextView nameTextView;
    private TextView numberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPassGeneratedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Get references to the views
        messageTextView = findViewById(R.id.messageTextView);
        imageView = findViewById(R.id.imageView);
        nameTextView = findViewById(R.id.nameTextView);
        numberTextView = findViewById(R.id.numberTextView);

        // Retrieve the incoming data from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String message = intent.getStringExtra("message");
            String username = intent.getStringExtra("username");
            String documentId = intent.getStringExtra("documentId");
            String misText = intent.getStringExtra("misText");

            // Set the data to the respective views
            messageTextView.setText(message);
            nameTextView.setText(username);
            numberTextView.setText("Unique Number: " + documentId);

            // Set the image (if needed) using an image loading library like Glide or Picasso
            // Example using Glide:
            Glide.with(this).load(R.drawable.mstwo).into(imageView);
        }
    }
}
