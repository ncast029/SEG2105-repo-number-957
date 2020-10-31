package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

public class EmployeeWelcomePage extends AppCompatActivity {

    TextView welcomeEmployee;
    String welcomeemployee;
    private Button log_out;
    private Button DL;
    private Button HC;
    private Button PI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_welcome_page);

        welcomeEmployee = findViewById(R.id.tvWelcomeEmployee);
        welcomeemployee = getIntent().getExtras().getString("Value");
        welcomeEmployee.setText("Welcome " + welcomeemployee + ", you are logged in as an employee.");

        log_out = (Button) findViewById(R.id.log_out_btn);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EmployeeWelcomePage.this, MainActivity.class));
            }
        });

        DL = (Button) findViewById(R.id.DLbtn);
        DL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EmployeeWelcomePage.this, DriversLicense.class));

            }
        });
        HC = (Button) findViewById(R.id.HCbtn);
        HC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EmployeeWelcomePage.this, MainActivity.class));
            }
        });
        PI = (Button) findViewById(R.id.PIbtn);
        PI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EmployeeWelcomePage.this, MainActivity.class));
            }
        });


    }
}
