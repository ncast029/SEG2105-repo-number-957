package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationOption extends AppCompatActivity {

    private Button ChooseCustomer, ChooseEmployee;
    private TextView ChooseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_option);
        setupUIViews();

        ChooseCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationOption.this, CustomerRegistration.class));
            }
        });

        ChooseEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationOption.this, EmployeeRegistration.class));
            }
        });
    }

    private void setupUIViews() {
        ChooseCustomer = (Button) findViewById(R.id.btnChooseCustomer);
        ChooseEmployee = (Button) findViewById(R.id.btnChooseEmployee);
        ChooseUser = (TextView)findViewById(R.id.tvChooseUser);
    }
}