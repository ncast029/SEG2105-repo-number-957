package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CustomerWelcomePage extends AppCompatActivity {

    TextView welcomeCustomer;
    String welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_customer_welcome_page);
        welcomeCustomer = findViewById(R.id.tvWelcomeCustomer);
//        welcome = getIntent().getExtras().getString("Value");
        welcomeCustomer.setText("Welcome " + firebaseAuth.getCurrentUser().getEmail() + ", you are logged in as an employee.");

    }
}