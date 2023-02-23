package com.example.mindsparkui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startScreen(View view) {
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginButton =findViewById(R.id.loginButton);
        if(username.getText().toString().equals("1") && password.getText().toString().equals("1")){
            Intent intent = new Intent(MainActivity.this, events.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
        }

//        Intent i=new Intent();
    }


}