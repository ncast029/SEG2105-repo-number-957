package com.example.appversion2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditEmployees extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        final EmployeeUserProfile user = EmployeeUserProfile.getEmployee(getIntent().getExtras().getString("email"));

        ((EditText) findViewById(R.id.fname_box)).setText(user.getEmployeeFirstName());
        ((EditText) findViewById(R.id.lname_box)).setText(user.getEmployeeLastName());
        ((EditText) findViewById(R.id.email_box)).setText(user.getEmployeeEmail());
        ((EditText) findViewById(R.id.pwd_box)).setText(user.getEmployeePassword());
        ((EditText) findViewById(R.id.emp_num_box)).setText(user.getEmployeeNumber());
        if (user.getAddress() != null) {
            ((EditText) findViewById(R.id.emp_addr_box)).setText(user.getAddress());
        }
        if (user.getPhoneNumber() != null) {
            ((EditText) findViewById(R.id.emp_pn_box)).setText(user.getPhoneNumber());
        }

        Button back = (Button) findViewById(R.id.back_btn);

        back.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(EditEmployees.this, EmployeeWelcomePage.class).putExtra("email", user.getEmployeeEmail()));
                                        finish();
                                    }
                                }

        );

        Button submit = (Button) findViewById(R.id.Submit);

        submit.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    String fName = ((EditText) findViewById(R.id.fname_box)).getText().toString();
                    String lName = ((EditText) findViewById(R.id.lname_box)).getText().toString();
                    String email = ((EditText) findViewById(R.id.email_box)).getText().toString();
                    String pwd = ((EditText) findViewById(R.id.pwd_box)).getText().toString();
                    String empNum = ((EditText) findViewById(R.id.emp_num_box)).getText().toString();
                    String addr = ((EditText) findViewById(R.id.emp_addr_box)).getText().toString();
                    String pn = ((EditText) findViewById(R.id.emp_pn_box)).getText().toString();
                    EmployeeUserProfile tempUser = new EmployeeUserProfile(fName,lName,email,pwd,empNum);
                    tempUser.setAddress(addr);
                    tempUser.setPhoneNumber(pn);
                    String validate = EmployeeUserProfile.validate(tempUser);
                    if (validate.equals("success")) {
                        user.setUserFirstName(fName);
                        user.setUserLastName(lName);
                        user.setUserEmail(email);
                        user.setUserPassword(pwd);
                        user.setEmployeeNumber(empNum);
                        user.setPhoneNumber(pn);
                        user.setAddress(addr);
                        Toast.makeText(EditEmployees.this, "Profile Changed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditEmployees.this, EmployeeWelcomePage.class).putExtra("email", user.getEmployeeEmail()));
                        finish();
                    } else {
                        Toast.makeText(EditEmployees.this, validate, Toast.LENGTH_SHORT).show();
                    }
                }
        });

    }
}
