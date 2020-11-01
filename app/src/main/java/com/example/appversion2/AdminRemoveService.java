package com.example.appversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class AdminRemoveService extends AppCompatActivity {

    private Button confirmRemovalOfService, backToAdminWelcome;
    private TextView pageInfoText, stateInfoText;
    private CheckBox stateOfService;
    private EditText serviceName;
    private String serviceNameString;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_service_page);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        confirmRemovalOfService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(validate())
                {
                    //Delete service called (name of service)
                }
            }
        };

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
        pageInfoText = (TextView)findViewById(R.id.tvCreateServiceText);
        serviceName = (EditText)findViewById(R.id.etServiceName);

        stateInfoText = (TextView)findViewById(R.id.tvServiceState);
        stateOfService = (CheckBox)findViewById(R.id.toggleServiceButton);

        confirmRemovalOfService = (Button)findViewById(R.id.confirmCreationButton);
        backToAdminWelcome = (Button)findViewById(R.id.backButton);
    }

    private Boolean validate() {
        Boolean result = false;
        serviceNameString = serviceName.getText().toString();

        if(serviceNameString.isEmpty()) {
            Toast.makeText(this, "Please make sure to name your service!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }
}
