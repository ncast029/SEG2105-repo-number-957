
package com.example.appversion2;

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

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    String name;
    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    EmployeeRegistration employeeRegistration = new EmployeeRegistration();
    CustomerRegistration customerRegistration = new CustomerRegistration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);


        Info.setText("No of attempts remaining: 5");



        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        final FirebaseUser employeeUser = firebaseAuth.getCurrentUser();
        final FirebaseUser customerUser = firebaseAuth.getCurrentUser();

        if(employeeUser == null) {
            finish();
            startActivity(new Intent(MainActivity.this, EmployeeWelcomePage.class));
        }

        if(customerUser == null) {
            finish();
            startActivity(new Intent(MainActivity.this, CustomerWelcomePage.class));
        }

        if (Email.toString() != "" ) {
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validateAdmin(Email.getText().toString(), Password.getText().toString());
                    validate(Email.getText().toString(), Password.getText().toString(),employeeUser,customerUser);
                }
            });
        }

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationOption.class));
            }
        });
    }

    private void validateAdmin(String userName, String userPassword) {
        if(userName.equals("Admin") && userPassword.equals("admin")) {
            Intent intent = new Intent(MainActivity.this, AdminWelcomePage.class);
            name = Name.getText().toString();
            intent.putExtra("Value", name);
            startActivity(intent);
            finish();
        }
        else {
            counter--;

            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if(counter == 0) {
                Login.setEnabled(false);
            }
        }
    }


    private void validate(String EmployeeEmail, String EmployeePassword, final FirebaseUser employeeUser,final FirebaseUser customerUser){

        progressDialog.setMessage("Loading, please wait.");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(EmployeeEmail, EmployeePassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() && (employeeUser.getEmail() != "")) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Employee Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, EmployeeWelcomePage.class);
                    name = Name.getText().toString();
                    i.putExtra("Value", name);
                    startActivity(i);
                    finish();
                }
                else if(task.isSuccessful() && (customerUser.getEmail() != "") ){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Customer Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, CustomerWelcomePage.class);
                    name = Name.getText().toString();
                    i.putExtra("Value", name);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No of attempts remaining: " + counter);
                    progressDialog.dismiss();
                    if(counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });

    }


}