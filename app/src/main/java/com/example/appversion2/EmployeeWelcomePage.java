package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class EmployeeWelcomePage extends AppCompatActivity {

    TextView welcomeEmployee;
    String welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_employee_welcome_page);
        welcomeEmployee = findViewById(R.id.tvWelcomeEmployee);
//        welcome = getIntent().getExtras().getString("Value");
        welcomeEmployee.setText("Welcome " + firebaseAuth.getCurrentUser().getEmail() + ", you are logged in as an employee.");

    }
}