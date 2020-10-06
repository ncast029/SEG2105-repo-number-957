package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class EmployeeWelcomePage extends AppCompatActivity {

    TextView welcomeEmployee;
    String welcomeemployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_welcome_page);
        welcomeEmployee = findViewById(R.id.tvWelcomeEmployee);
        welcomeemployee = getIntent().getExtras().getString("Value");
        welcomeEmployee.setText("Welcome " + welcomeemployee + ", you are logged in as an employee.");

    }
}