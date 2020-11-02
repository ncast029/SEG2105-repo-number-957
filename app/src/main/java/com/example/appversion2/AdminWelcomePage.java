package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminWelcomePage extends AppCompatActivity {

    TextView welcomeAdmin;
    String welcome;

    private Button create_Service_Button;
    private Button edit_service_button;
    private Button remove_service_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome_page);
        welcomeAdmin = findViewById(R.id.tvWelcomeAdmin);
        //welcome = getIntent().getExtras().getString("Value");
        welcomeAdmin.setText("Welcome admin.");

        setupUIViews();

        create_Service_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminWelcomePage.this, AdminCreateService.class));
            }
        });

        edit_service_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminWelcomePage.this, AdminEditService.class));
            }
        });

        remove_service_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminWelcomePage.this, AdminRemoveService.class));
            }
        });
    }

    private void setupUIViews() {
        create_Service_Button = (Button)findViewById(R.id.CreateServiceButton);
        edit_service_button = (Button)findViewById(R.id.EditServiceButton);
        remove_service_button = (Button)findViewById(R.id.RemoveServiceButton);
    }
}