package com.paras.foodhub.SignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.paras.foodhub.R;

public class SignUpActivity extends AppCompatActivity {

    ImageView imgBack, imgPasswordCloseEye;
    EditText etvEmailLI, etvPasswordLI;
    Button btnForgotPassword, btnLogin;
    TextView txtSignUp;
    CardView cvFacebook, cvGoogle;
    String password = "Hide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}