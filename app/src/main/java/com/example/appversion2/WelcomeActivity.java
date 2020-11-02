package com.example.appversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WelcomeActivity extends AppCompatActivity {
    private TextView welcome,role;
    private Button continueButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome = (TextView) findViewById(R.id.tvWelcomeName);
        role = (TextView) findViewById(R.id.tvWelcomeRole);
        continueButton = (Button) findViewById(R.id.btnContinue);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                welcome.setText("Welcome " + userProfile.getUserFirstName());
                role.setText("You are logged in as " + userProfile.getUserRole());


                continueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(WelcomeActivity.this, AdminWelcomePage.class));
                        //setContentView(R.layout.activity_admin);
                        if (userProfile.getUserRole().equals("Admin") ) {
                            startActivity(new Intent(WelcomeActivity.this, AdminWelcomePage.class));
                            setContentView(R.layout.activity_admin_welcome_page);

                        } else if (userProfile.getUserRole().equals("Employee")) {
                            Intent employee = new Intent(WelcomeActivity.this, EmployeeWelcomePage.class);
                            startActivity(employee);
                            setContentView(R.layout.activity_employee_welcome_page);

                        } else {
                            startActivity(new Intent(WelcomeActivity.this, CustomerWelcomePage.class));

                        }
                    }
                });


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WelcomeActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }


        });

        Button log_out = (Button) findViewById(R.id.log_out_btn);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });

        Button DL = (Button) findViewById(R.id.DLbtn);
        DL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, DriversLicense.class));
            }
        });
        Button HC = (Button) findViewById(R.id.HCbtn);
        HC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
        Button PI = (Button) findViewById(R.id.PIbtn);
        PI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });



    }




}