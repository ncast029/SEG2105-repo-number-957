package com.example.appversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    public static EditText userFirstName;
    public static EditText userLastName;
    EditText userEmail;
    EditText userPassword;
    private Button register;
    //public static Spinner userRole;
    private FirebaseAuth  firebaseAuth;
    String firstName, lastName, password, email, role;
    private Button goBack;
    private RadioGroup userRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {

                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();



                    if (role.equals("Employee")) {
                        EmployeeUserProfile newEmployee = new EmployeeUserProfile(firstName, lastName, user_email, user_password, "-1");
                        String success = EmployeeUserProfile.validate(newEmployee);
                        if (success.equals("success")) {
                            EmployeeUserProfile.employees.add(newEmployee);
                            Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Registration Failed: " + success, Toast.LENGTH_SHORT).show();
                        }
                    } else {

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserData();

                                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();


                            }


                        }
                    });}
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    }

    private void setupUIViews() {
        userFirstName = (EditText)findViewById(R.id.etFirstName);
        userLastName = (EditText)findViewById(R.id.etLastName);
        userEmail = (EditText)findViewById(R.id.etEmail);
        userPassword = (EditText)findViewById(R.id.etPassword);
        register = (Button)findViewById(R.id.btnRegister);
        //userRole = (Spinner)findViewById(R.id.spinRole);
        goBack = (Button)findViewById(R.id.tvLogin);
        userRole = (RadioGroup) findViewById(R.id.roleGroup);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.id.spinRole, R.layout.support_simple_spinner_dropdown_item);
        //userRole.setAdapter(adapter);
    }

    private Boolean validate() {
        Boolean result = false;

        firstName = userFirstName.getText().toString();
        lastName = userLastName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        int checkedButton = userRole.getCheckedRadioButtonId();


        if(firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || email.isEmpty() || checkedButton == -1) {
            Toast.makeText(this, "Please enter all your details.", Toast.LENGTH_SHORT).show();
        }

        else {
            result = true;
            role = ((RadioButton) findViewById(checkedButton)).getText().toString();
        }

        return result;
    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile profile = new UserProfile(email, password, firstName, lastName, role);
        myRef.setValue(profile);
    }
}