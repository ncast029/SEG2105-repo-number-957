package com.example.appversion2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdminCreateService extends AppCompatActivity {

    private Button confirmCreationOfService, backToAdminWelcome;
    private CheckBox firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomers;
    private EditText serviceName;
    private String serviceNameString;
    Context temp;

    private static ArrayList<ServiceProfile> serviceArrayListForCreate = new ArrayList<ServiceProfile>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_service_page);
        setupUIViews();
        temp = this;

        serviceArrayListForCreate = GlobalArrays.getServiceArrayList();

        confirmCreationOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    ServiceProfile service = new ServiceProfile(serviceNameString, firstName.isChecked(), secondName.isChecked(),
                            dateOfBirth.isChecked(), address.isChecked(), licenseType.isChecked(), proofOfResidence.isChecked(),
                            ProofOfStatus.isChecked(), photoOfTheCustomers.isChecked());
                    GlobalArrays.addServiceToArrayList(service);
                    Toast.makeText(temp, "Sucessfully created " + serviceNameString + " service.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backToAdminWelcome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AdminCreateService.this, AdminWelcomePage.class));
            }
        });
    }

    private void setupUIViews() {
        serviceName = (EditText)findViewById(R.id.etServiceName);
        firstName = (CheckBox)findViewById(R.id.FirstNameButton);
        secondName = (CheckBox)findViewById(R.id.LastNameButton);
        dateOfBirth = (CheckBox)findViewById(R.id.DOBButton);
        address = (CheckBox)findViewById(R.id.addressButton);
        licenseType = (CheckBox)findViewById(R.id.LicenseType);

        proofOfResidence = (CheckBox)findViewById(R.id.ProofOfResidenceButton);
        ProofOfStatus = (CheckBox)findViewById(R.id.ProofOfStatusButton);
        photoOfTheCustomers = (CheckBox)findViewById(R.id.PhotoButton);

        confirmCreationOfService = (Button)findViewById(R.id.confirmCreationButton);
        backToAdminWelcome = (Button)findViewById(R.id.Gobackstatesnotsaved);
    }

    private Boolean validate() {
        serviceNameString = serviceName.getText().toString();
        if(serviceNameString.isEmpty()) {
            Toast.makeText(temp, "Please make sure to name your service!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            for(int i = 0; i < serviceArrayListForCreate.size(); i++)
            {
                if(serviceArrayListForCreate.get(i).getServiceName().equals(serviceNameString))
                {
                    Toast.makeText(temp, "Service name already in use. Please choose a new name!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            return true;
        }
    }
}
