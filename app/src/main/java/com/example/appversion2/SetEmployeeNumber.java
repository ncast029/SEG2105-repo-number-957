package com.example.appversion2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SetEmployeeNumber extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_number);

        Button submit = (Button) findViewById(R.id.submit_emp_number);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText eNum = (EditText) findViewById(R.id.emp_num_box);
                String employeeNumber = eNum.getText().toString();
                String temp = EmployeeUserProfile.validateEmployeeNumber(employeeNumber);
                if (temp.equals("success")) {
                    Toast.makeText(SetEmployeeNumber.this, "Employee Number Accepted", Toast.LENGTH_SHORT).show();
                    EmployeeUserProfile.getEmployee(getIntent().getExtras().getString("email")).setEmployeeNumber(employeeNumber);
                    finish();
                } else {
                    Toast.makeText(SetEmployeeNumber.this, temp, Toast.LENGTH_SHORT).show();
                }




            }
        });


    }






}
