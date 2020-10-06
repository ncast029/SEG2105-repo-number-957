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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerRegistration extends AppCompatActivity {

    private String CustomerRole = "Customer";
    private EditText CustomerFirstName, CustomerLastName, CustomerPassword;
    public EditText CustomerEmail;
    private Button CustomerRegButton;
    private TextView CustomerLogin;
    private TextView CustomerCharacters;
    String firstName, lastName, email, password, role;
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

                    firebaseAuth.createUserWithEmailAndPassword(customer_email, customer_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                sendUserData();
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
        CustomerCharacters = (TextView)findViewById(R.id.tvCustomerCharacters);
    }

    private Boolean validate() {
        Boolean result = false;

        firstName = CustomerFirstName.getText().toString();
        lastName = CustomerLastName.getText().toString();
        password = CustomerPassword.getText().toString();
        email = CustomerEmail.getText().toString();
        role = CustomerRole;

        if(firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter ALL details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        CustomerUserProfile customerUserProfile = new CustomerUserProfile(firstName, lastName, email, password, role);
        myRef.setValue(customerUserProfile);
    }
}