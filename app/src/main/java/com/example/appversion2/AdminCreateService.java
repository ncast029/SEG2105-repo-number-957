package com.example.appversion2;

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

    //public AdminCreateService(String serviceName, boolean firstName, boolean secondName, boolean dateOfBirth, boolean address, boolean licenseType, boolean proofOfResidence, boolean ProofOfStatus, boolean photoOfTheCustomer)
    //{
       // super(serviceName, firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomer);
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_service_page);
        setupUIViews();
        temp = this;



        confirmCreationOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    //this line will call the SericeProfile class and create a new service with the paramters as requested by the admin
                    ServiceProfile service = new ServiceProfile(serviceNameString, firstName.isChecked(), secondName.isChecked(),
                            dateOfBirth.isChecked(), address.isChecked(), licenseType.isChecked(), proofOfResidence.isChecked(),
                            ProofOfStatus.isChecked(), photoOfTheCustomers.isChecked());
                    ServiceProfile.getArrayList().add(service);
                    Toast.makeText(temp, "Service created", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AdminCreateService.this, AdminWelcomePage.class));
                    finish();
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
        licenseType = (CheckBox)findViewById(R.id.LicenseTypeButton);

        proofOfResidence = (CheckBox)findViewById(R.id.ProofOfResidenceButton);
        ProofOfStatus = (CheckBox)findViewById(R.id.ProofOfStatusButton);
        photoOfTheCustomers = (CheckBox)findViewById(R.id.PhotoButton);

        confirmCreationOfService = (Button)findViewById(R.id.confirmCreationButton);
        backToAdminWelcome = (Button)findViewById(R.id.Gobackstatesnotsaved);
    }

    private Boolean validate() {
        Boolean result = true;
        Boolean singular = true;
        serviceNameString = serviceName.getText().toString();

        ArrayList<ServiceProfile> serviceArrayList = ServiceProfile.getArrayList();
        for(int i = 0; i < serviceArrayList.size(); i++)
        {
            if(serviceArrayList.get(i).getServiceName().equals(serviceNameString))
            {
                Toast.makeText(temp, "Service name already in use. Please choose a new name!", Toast.LENGTH_SHORT).show();
                singular = false;
            }
        }

        if(serviceNameString.isEmpty()) {
            Toast.makeText(temp, "Please make sure to name your service!", Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result && singular;
    }
}
