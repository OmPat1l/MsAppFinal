package com.example.mindsparkui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mindsparkui.databinding.ActivityEventsBinding;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mindsparkui.databinding.ActivityEventsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class events extends AppCompatActivity {
    private ImageView imageView;
    private TextView userName;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String username ;
    String imageUrl;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityEventsBinding binding;
    private ViewPager viewPager;
    private LinearLayout circleIndicatorLayout;
    private int[] imageResources = {R.drawable.msone, R.drawable.mstwo, R.drawable.msthree};
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityEventsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarEvents.toolbar);
        binding.appBarEvents.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact Developer: omajitsinhpatil@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_events);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        // Your other code

         navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);

        ImageView imageView = headerView.findViewById(R.id.imageView);
        TextView userName = headerView.findViewById(R.id.userName);

        // Get the passed data from MainActivity
        Intent intent = getIntent();
        if (intent != null) {
             username = intent.getStringExtra("username");
             imageUrl = intent.getStringExtra("imageUrl");

            // Set the username in the header TextView
            userName.setText(username);

            // Load and display the image using an image loading library like Glide or Picasso
            // Example using Glide:
            Glide.with(this).load(imageUrl).into(imageView);
            Toast.makeText(this, "url "+ imageUrl, Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.events, menu);
        return true;
    }
    public void startQuery(View view){
//        Toast.makeText(this, "Hello this is function", Toast.LENGTH_SHORT).show();
        Intent buff=new Intent(events.this,FAQans.class);
        startActivity(buff);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_events);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
//    public void startLoad(View view) {
////
//        EditText mis = findViewById(R.id.username1);
//        EditText email = findViewById(R.id.password1);
//
//        String emailText = email.getText().toString();
//        String misText = mis.getText().toString();
//        Toast.makeText(this, "passes would be created", Toast.LENGTH_SHORT).show();
//
////        Intent i = new Intent(events.this, loadingpass.class);
////        i.putExtra("email", emailText);
////        i.putExtra("password", passwordText);
////        startActivity(i);
//    }
public void startLoad(View view) {
    EditText misEditText = findViewById(R.id.username1);
    EditText emailEditText = findViewById(R.id.password1);


    String emailText = emailEditText.getText().toString();
    String misText = misEditText.getText().toString();
    if(TextUtils.isEmpty(misText) ||   TextUtils.isEmpty(emailText)) {
        Toast.makeText(this, "Enter Valid Details", Toast.LENGTH_SHORT).show();
        return;
    }
    // Start the loadingpass activity
    Intent loadingIntent = new Intent(events.this, loadingpass.class);
    startActivity(loadingIntent);

    // Create a new Firestore instance
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Reference the "passesCreated" collection
    CollectionReference passesCollection = db.collection("passesCreated");

    // Query the collection for documents that match the given email and misText
    Query query = passesCollection.whereEqualTo("email", emailText);

    // Execute the query
    query.get().addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
            QuerySnapshot querySnapshot = task.getResult();

            // Check if a document with the given email and misText exists
            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                // Document already exists, retrieve its details
                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                String documentId = documentSnapshot.getId();
                String existingUsername = documentSnapshot.getString("username");

                // Start the passGenerated activity and pass the necessary data
                Intent intent = new Intent(events.this, passGenerated.class);
                intent.putExtra("username", existingUsername);
                intent.putExtra("misText", misText);

                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("documentId", documentId);
                intent.putExtra("message", "Pass already created");
                startActivity(intent);
            } else {
                // Document doesn't exist, create a new one
                Map<String, Object> passData = new HashMap<>();
                passData.put("username", username);
                passData.put("email", emailText);
                passData.put("misText", misText);

                // Add the new document to the "passesCreated" collection
                ((CollectionReference) passesCollection).add(passData)
                        .addOnSuccessListener(documentReference -> {
                            String documentId = documentReference.getId();

                            // Start the passGenerated activity after a delay
                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                Intent passGeneratedIntent = new Intent(events.this, passGenerated.class);
                                passGeneratedIntent.putExtra("username", username);
                                passGeneratedIntent.putExtra("imageUrl", imageUrl);
                                passGeneratedIntent.putExtra("misText", misText);
                                passGeneratedIntent.putExtra("documentId", documentId);
                                passGeneratedIntent.putExtra("message", "Pass created successfully");
                                startActivity(passGeneratedIntent);
                            }, 2000); // 2 seconds delay
                        })
                        .addOnFailureListener(e -> {
                            // Handle the failure case
                            Toast.makeText(this, "Failed to create pass", Toast.LENGTH_SHORT).show();
                        });
            }
        } else {
            // Handle the failure case
            Toast.makeText(this, "Failed to retrieve data from Firebase", Toast.LENGTH_SHORT).show();
        }
    });

    Toast.makeText(this, "Passes would be created", Toast.LENGTH_SHORT).show();
}


    public void logout(MenuItem item) {
        // Perform logout actions here, such as signing out the user
        // For example, if you're using Firebase Authentication:
        FirebaseAuth.getInstance().signOut();
        clearAuthState();

        // Show a toast message to indicate successful logout
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();

        // Start the MainActivity and clear the back stack
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void clearAuthState() {
        // Clear any cached user credentials or authentication state
        // Implement the necessary steps based on your authentication provider
        clearCacheAndUserData();
        // Example: Clearing Firebase Authentication state
        FirebaseAuth.getInstance().signOut();
    }


    private void clearCacheAndUserData() {
        // Clear cache directory
        File cacheDir = getCacheDir();
        if (cacheDir != null && cacheDir.isDirectory()) {
            File[] cacheFiles = cacheDir.listFiles();
            if (cacheFiles != null) {
                for (File cacheFile : cacheFiles) {
                    cacheFile.delete();
                }
            }
        }

        // Clear app data directory
        File dataDir = getFilesDir().getParentFile();
        if (dataDir != null && dataDir.isDirectory()) {
            String[] dataFiles = dataDir.list();
            if (dataFiles != null) {
                for (String dataFile : dataFiles) {
                    if (!dataFile.equals("lib")) {
                        deleteRecursive(new File(dataDir, dataFile));
                    }
                }
            }
        }

        // Clear shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().clear().apply();
    }

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }





}