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

    private static ArrayList<ServiceProfile> serviceArrayListForRemove = new ArrayList<ServiceProfile>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_service_page);
        setupUIViews();
        temp = this;

        serviceArrayListForRemove = GlobalArrays.getServiceArrayList();

        confirmRemovalOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(validate())
                {
                    for(int i = 0; i < serviceArrayListForRemove.size(); i++)
                    {
                        if(serviceArrayListForRemove.get(i).getServiceName().equals(serviceNameString))
                        {
                            GlobalArrays.removeServiceFromArrayList(i);
                            Toast.makeText(temp, "Sucessfully removed" + serviceNameString + " service.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
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
        serviceNameString = serviceName.getText().toString();

        if(serviceNameString.isEmpty()) {
            Toast.makeText(temp, "Please provide a service name to remove", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            for(int i = 0; i < serviceArrayListForRemove.size(); i++)
            {
                if(serviceArrayListForRemove.get(i).getServiceName().equals(serviceNameString))
                {
                    return true;
                }
            }
            Toast.makeText(temp, "Please provide a valid service name to remove", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
