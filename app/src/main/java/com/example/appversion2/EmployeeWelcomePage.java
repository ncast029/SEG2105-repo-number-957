package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class EmployeeWelcomePage extends AppCompatActivity {

    private EmployeeUserProfile user;
    TextView welcomeEmployee;
    private String welcomeemployee;
    private Button log_out;
    private Button info;
    private Button forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_welcome_page);

        user = EmployeeUserProfile.getEmployee(getIntent().getExtras().getString("email"));

        if (user.getEmployeeNumber().equals("-1")) {
            startActivity(new Intent(EmployeeWelcomePage.this, SetEmployeeNumber.class).putExtra("email", user.getEmployeeEmail()));
        }

        welcomeEmployee = findViewById(R.id.tvWelcomeEmployee);
        welcomeemployee = user.getEmployeeFirstName();
        welcomeEmployee.setText("Welcome " + welcomeemployee + ", you are logged in as an employee.");



        info = (Button) findViewById(R.id.info_btn);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EmployeeWelcomePage.this, EditEmployees.class).putExtra("email", user.getEmployeeEmail()));
                finish();

            }
        });

        forms = (Button) findViewById(R.id.form_btn);
        forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EmployeeWelcomePage.this, ProcessApplications.class));
            }
        });


        log_out = (Button) findViewById(R.id.log_out_btn);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EmployeeWelcomePage.this, MainActivity.class));
            }
        });

    }
}
