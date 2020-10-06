package com.example.appversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmployeeRegistration extends AppCompatActivity {

    private final String role = "Employee";
    private EditText EmployeeFirstName, EmployeeLastName, EmployeePassword, EmployeeNumber;
    public EditText EmployeeEmail;
    private Button EmployeeRegButton;
    private TextView EmployeeLogin;
    String firstName, lastName, email, password, number;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();


        EmployeeRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    //Upload data to database
                    String employee_email = EmployeeEmail.getText().toString().trim();
                    String employee_password = EmployeePassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(employee_email, employee_password, role).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(EmployeeRegistration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(EmployeeRegistration.this, MainActivity.class));
                            } else {
                                Toast.makeText(EmployeeRegistration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        EmployeeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeRegistration.this, MainActivity.class));
            }
        });
    }

    private void setupUIViews() {
        EmployeeFirstName = (EditText)findViewById(R.id.etEmployeeFirstName);
        EmployeeLastName = (EditText)findViewById(R.id.etEmployeeLastName);
        EmployeeEmail = (EditText)findViewById(R.id.etEmployeeEmail);
        EmployeePassword = (EditText)findViewById(R.id.etEmployeePassword);
        EmployeeNumber = (EditText)findViewById(R.id.etEmployeeNumber);
        EmployeeRegButton = (Button)findViewById(R.id.btnEmployeeRegister);
        EmployeeLogin = (TextView)findViewById(R.id.tvEmployeeLogin);
    }

    private Boolean validate() {
        Boolean result = false;

        firstName = EmployeeFirstName.getText().toString();
        lastName = EmployeeLastName.getText().toString();
        password = EmployeePassword.getText().toString();
        email = EmployeeEmail.getText().toString();
        number = EmployeeNumber.getText().toString();

        if(firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || email.isEmpty() || number.isEmpty()) {
            Toast.makeText(this, "Please enter ALL details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }
}