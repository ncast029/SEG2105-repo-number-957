package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AdminWelcomePage extends AppCompatActivity {

    TextView welcomeAdmin;
    String welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome_page);
        welcomeAdmin = findViewById(R.id.tvWelcomeAdmin);
        welcome = getIntent().getExtras().getString("Value");
        welcomeAdmin.setText("Welcome " + welcome + ", you are logged in as admin.");
    }
}