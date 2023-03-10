package com.example.rental_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //splash screen wait for 2 sec and then launch login screen

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (user!= null){
                    // if the user is already logged in then it will go to home screen
                    // he do not need to login again
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();

                }

            }
        },4000);
    }
}