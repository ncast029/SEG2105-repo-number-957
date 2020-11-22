package com.example.appversion2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProcessApplications extends AppCompatActivity {

    public static Activity thisActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_applications);
        thisActivity = this;

        final Spinner type_spinner = (Spinner) findViewById(R.id.type_spinner);
        final Spinner name_spinner = (Spinner) findViewById(R.id.name_spinner);

        ArrayList<String> types=new ArrayList<String>();
        types.add("<>");

        final ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>();

        //ArrayList<String> names=new ArrayList<String>();
        //names.add("<>");

        final ArrayList<String> onOpen = new ArrayList<String>();
        onOpen.add("<>");

        for (int i=0; i<GlobalArrays.forms.size(); i++) {
            types.add(GlobalArrays.forms.get(i).getFormType());
        }

        for (int i=0; i<types.size(); i++) {
            names.add(new ArrayList<String>());
        }

        for (int i=0; i<GlobalArrays.forms.size(); i++) {
            for (int j = 0; j < types.size(); j++) {
                if (types.get(j).equals(GlobalArrays.forms.get(i).getFormType())) {
                    names.get(j).add(GlobalArrays.forms.get(i).getFirstName() + " " + GlobalArrays.forms.get(i).getLastName());
                }
            }
        }
        //names.add(GlobalArrays.forms.get(i).getFirstName() + " " + GlobalArrays.forms.get(i).getLastName());


        ArrayAdapter<String> types_adapter = new ArrayAdapter<String>(ProcessApplications.this,android.R.layout.simple_spinner_item,types);
        types_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(types_adapter);

        //type_spinner.setSelection(1);

        ArrayAdapter<String> names_adapter = new ArrayAdapter<String>(ProcessApplications.this,android.R.layout.simple_spinner_item,onOpen);
        names_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name_spinner.setAdapter(names_adapter);

        //name_spinner.setSelection(1);


        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //((Button) findViewById(R.id.access_form_btn)).setText(type_spinner.getItemAtPosition(position).toString());
                if (type_spinner.getSelectedItem().toString().equals("<>")) {
                    ArrayAdapter<String> names_adapter = new ArrayAdapter<String>(ProcessApplications.this,android.R.layout.simple_spinner_item,onOpen);
                    names_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    name_spinner.setAdapter(names_adapter);
                } else {
                    ArrayAdapter<String> names_adapter = new ArrayAdapter<String>(ProcessApplications.this, android.R.layout.simple_spinner_item, names.get(position));
                    names_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    name_spinner.setAdapter(names_adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button viewButton = (Button) findViewById(R.id.access_form_btn);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = type_spinner.getSelectedItem().toString();
                String name = name_spinner.getSelectedItem().toString();
                if (type != null && !type.equals("<>") && name != null && !name.equals("<>") && !name.equals("")) {
                    Intent tmp = new Intent(ProcessApplications.this, ViewApplication.class);
                    tmp.putExtra("name",name);
                    tmp.putExtra("type",type);
                    startActivity(tmp);
                } else {
                    Toast.makeText(ProcessApplications.this, "Please select a form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((Button) findViewById(R.id.goBackBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}

