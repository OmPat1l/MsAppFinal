package com.example.mindsparkui;//package com.example.mindsparkui;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}



//
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//        import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//        import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//        import com.google.android.gms.tasks.Task;
//        import com.google.firebase.auth.AuthCredential;
//        import com.google.firebase.auth.FirebaseAuth;
//        import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure Google Sign-In options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Create Google Sign-In client
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Set click listener for the sign-in button
        Button signInButton = findViewById(R.id.loginButtonGoogle);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    // Start the Google Sign-In flow
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // Handle the result of the Google Sign-In flow
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign-In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign-In failed
                Toast.makeText(this, "Google Sign-In failed: " + e.getStatusCode(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Authenticate with Firebase using the Google account
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign-in success, update UI with the signed-in user's information
                            Intent intent = new Intent(MainActivity.this, events.class);
                            startActivity(intent);
                        } else {
                            // If sign-in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    EditText username;
    EditText password;
    Button loginButton;
    public void startScreen(View view) {
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginButton =findViewById(R.id.loginButton);
        if(username.getText().toString().equals("1") && password.getText().toString().equals("1")){
            Intent intent = new Intent(MainActivity.this, events.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
        }}
}


////        Intent i=new Intent();