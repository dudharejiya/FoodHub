    package com.paras.foodhub.SplashScreen;
    
    import androidx.appcompat.app.AppCompatActivity;
    
    import android.content.Intent;
    import android.os.Bundle;
    import android.os.Handler;
    
    import com.paras.foodhub.MainActivity;
    import com.paras.foodhub.R;
    
    public class Activity_SplashScreen extends AppCompatActivity {
    
        private static final long SPLASH_TIMEOUT = 2000;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);
    
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This code will be executed after the splash timeout
                    Intent mainIntent = new Intent(Activity_SplashScreen.this, MainActivity.class); // Replace with your main activity
                    startActivity(mainIntent);
                    finish(); // Close the splash screen activity
                }
            }, SPLASH_TIMEOUT);
        }
    }