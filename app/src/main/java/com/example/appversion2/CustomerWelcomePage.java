package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerWelcomePage extends AppCompatActivity {

    TextView welcomeCustomer;
    String welcomecustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_welcome_page);

        welcomeCustomer = findViewById(R.id.tvWelcomeCustomer);
        welcomecustomer = getIntent().getExtras().getString("Value");
        welcomeCustomer.setText("Welcome " + welcomecustomer + ", you are logged in as an customer.");



    }
}