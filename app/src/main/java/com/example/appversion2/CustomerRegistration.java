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

public class CustomerRegistration extends AppCompatActivity {

    private final String role = "Customer";
    private EditText CustomerFirstName, CustomerLastName, CustomerPassword;
    public EditText CustomerEmail;
    private Button CustomerRegButton;
    private TextView CustomerLogin;
    String firstName, lastName, email, password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();


        CustomerRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    //Upload data to database
                    String customer_email = CustomerEmail.getText().toString().trim();
                    String customer_password = CustomerPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(customer_email, customer_password, role).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(CustomerRegistration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(CustomerRegistration.this, MainActivity.class));
                            } else {
                                Toast.makeText(CustomerRegistration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        CustomerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerRegistration.this, MainActivity.class));
            }
        });
    }

    private void setupUIViews() {
        CustomerFirstName = (EditText)findViewById(R.id.etCustomerFirstName);
        CustomerLastName = (EditText)findViewById(R.id.etCustomerLastName);
        CustomerEmail = (EditText)findViewById(R.id.etCustomerEmail);
        CustomerPassword = (EditText)findViewById(R.id.etCustomerPassword);
        CustomerRegButton = (Button)findViewById(R.id.btnCustomerRegister);
        CustomerLogin = (TextView)findViewById(R.id.tvCustomerLogin);
    }

    private Boolean validate() {
        Boolean result = false;

        firstName = CustomerFirstName.getText().toString();
        lastName = CustomerLastName.getText().toString();
        password = CustomerPassword.getText().toString();
        email = CustomerEmail.getText().toString();

        if(firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter ALL details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }
}