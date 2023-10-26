package com.paras.foodhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.paras.foodhub.Login.LoginActivity;
import com.paras.foodhub.SignUp.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    CardView cvFacebook, cvGoogle;
    Button btnSkip;
    TextView txtAlSignIn, btnEmailPhone;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvFacebook = findViewById(R.id.cvFacebook);
        cvGoogle = findViewById(R.id.cvGoogle);
        btnSkip = findViewById(R.id.btnSkip);
        btnEmailPhone = findViewById(R.id.btnEmailPhone);
        txtAlSignIn = findViewById(R.id.txtAlSignIn);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Skip Clicked...", Toast.LENGTH_SHORT).show();
            }
        });

        cvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Facebook Login...", Toast.LENGTH_SHORT).show();
            }
        });

        cvGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Google Login...", Toast.LENGTH_SHORT).show();
            }
        });

        btnEmailPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Email or Phone SignUp", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        txtAlSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Already Signed Up. SignIn Now", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}



/*
package com.paras.foodhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.paras.foodhub.Login.LoginActivity;
import com.paras.foodhub.SignUp.SignUpActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    CardView cvFacebook, cvGoogle;
    Button btnSkip;
    TextView txtAlSignIn, btnEmailPhone;
    FirebaseAuth firebaseAuth;
    private static final int REQ_ONE_TAP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvFacebook = findViewById(R.id.cvFacebook);
        cvGoogle = findViewById(R.id.cvGoogle);
        btnSkip = findViewById(R.id.btnSkip);
        btnEmailPhone = findViewById(R.id.btnEmailPhone);
        txtAlSignIn = findViewById(R.id.txtAlSignIn);

        firebaseAuth = FirebaseAuth.getInstance();

        // Set up click listeners
        setButtonListeners();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is signed in (non-null) and update the UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            // User is signed in, you can perform actions like updating the UI or navigating to a user-specific screen.
            // For example, you can show a "Sign Out" button or navigate to a user-specific activity.
            // reload(); // You can call your custom method here.
        }
    }

    private void setButtonListeners() {
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Skip Clicked...");
            }
        });

        cvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Facebook Login...");
            }
        });

        cvGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement Google Sign-In using Firebase Authentication
                startGoogleSignIn();
            }
        });

        btnEmailPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Email or Phone SignUp");
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        txtAlSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void startGoogleSignIn() {
        // Implement Google Sign-In using Firebase Authentication
        // You can include the Google Sign-In code here.
        BeginSignInRequest signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();

        // Use the `Identity` API to begin the Google Sign-In process
        Identity.getSignInClient(this).beginSignIn(signInRequest)
                .addOnSuccessListener(this::onSignInSuccess)
                .addOnFailureListener(this::onSignInFailure);
    }

    private void onSignInSuccess(BeginSignInResult result) {
        // Handle the successful sign-in result
        // You may get a Credential for the signed-in user and proceed with Firebase Authentication.
        SignInCredential googleCredential = result.getCredential();
        if (googleCredential != null) {
            String idToken = googleCredential.getGoogleIdToken();
            if (idToken != null) {
                AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                firebaseAuth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign-in fails, display a message to the user.
                                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                                    updateUI(null);
                                }
                            }
                        });
            }
        }
    }

    private void onSignInFailure(Exception e) {
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            int statusCode = apiException.getStatusCode();
            if (statusCode == CommonStatusCodes.CANCELED) {
                // Sign-in was canceled by the user
                Log.e(TAG, "Sign-in canceled by the user");
            } else {
                // Handle other sign-in errors
                Log.e(TAG, "Sign-in failed with error code: " + statusCode);
            }
        }
    }
    private void updateUI(FirebaseUser user) {
        // Implement the logic to update your user interface when the user is signed in.
        // This may include showing the user's profile information or navigating to a user-specific screen.
        if (user != null) {
            showToast("Signed in as: " + user.getDisplayName());
            // Update UI with user details.
            // For example, you can show a "Sign Out" button or navigate to a user-specific activity.
        }
    }
}
*/
