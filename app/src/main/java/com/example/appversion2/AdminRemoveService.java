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

public class AdminRemoveService extends AppCompatActivity {

    private Button confirmRemovalOfService, backToAdminWelcome;
    private EditText serviceName;
    private String serviceNameString;
    Context temp;
    /*
    public AdminRemoveService(String serviceName, boolean firstName, boolean secondName, boolean dateOfBirth, boolean address, boolean licenseType, boolean proofOfResidence, boolean ProofOfStatus, boolean photoOfTheCustomer)
    {
        super(serviceName, firstName, secondName, dateOfBirth, address, licenseType, proofOfResidence, ProofOfStatus, photoOfTheCustomer);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_service_page);
        setupUIViews();

        confirmRemovalOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(validate())
                {
                    //Delete service called (name of service)
                    ArrayList<ServiceProfile> serviceArrayList = ServiceProfile.getArrayList();

                    for(int i = 0; i < serviceArrayList.size(); i++)
                    {
                        if(serviceArrayList.get(i).getServiceName().equals(serviceNameString))
                        {
                            serviceArrayList.remove(i);
                        }
                    }
                    Toast.makeText(temp, "Service change successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AdminRemoveService.this, AdminWelcomePage.class));
                    finish();
                }
            }
        });

        backToAdminWelcome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AdminRemoveService.this, AdminWelcomePage.class));
            }
        });
    }



    private void setupUIViews() {
        serviceName = (EditText)findViewById(R.id.etServiceName);

        confirmRemovalOfService = (Button)findViewById(R.id.ConfirmRemoveService);
        backToAdminWelcome = (Button)findViewById(R.id.backToAdminButton);
    }

    private Boolean validate() {
        boolean result = false;
        serviceNameString = serviceName.getText().toString();

        ArrayList<ServiceProfile> serviceArrayList = ServiceProfile.getArrayList();
        for(int i = 0; i < serviceArrayList.size(); i++)
        {
            if(serviceArrayList.get(i).getServiceName().equals(serviceNameString))
            {
                result = true;
            }
        }

        if(serviceNameString.isEmpty()) {
            Toast.makeText(this, "Please provide a service name", Toast.LENGTH_SHORT).show();
        }
        return result;
    }
}
