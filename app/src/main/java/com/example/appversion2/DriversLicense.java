package com.example.appversion2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.ArrayList;

public class DriversLicense extends AppCompatActivity {

    private String rate;
    private ArrayList<File> files;

    public String getRate() {
        return rate;
    }
    public void setRate(String newRate) {
        rate = newRate;
    }

    protected void onCreate(Bundle savedInstanceState) {
        rate = "120.00";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_license_registration);

        Button helpButton = (Button) findViewById(R.id.hlpbtn);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window token
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });

            }
        });

        /*
        Button photoButton = (Button) findViewById(R.id.uibtn);
        photoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(DriversLicense.this);
                builder.setTitle("Upload Photo");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo"))
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File("", "temp.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            startActivityForResult(intent, 1);
                        }
                        else if (options[item].equals("Choose from Gallery"))
                        {
                            Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, 2);
                        }
                        else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
                        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

                    }
                });
                builder.show();
            }
        });
        */



        /*
        Button fileButton = (Button) findViewById(R.id.filebtn);
        fileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {

            }
        });
        */

        Button submitButton = (Button) findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DriversLicense.this);
                builder.setCancelable(true);
                builder.setTitle("Confirm Driver's License Form Submission");
                builder.setMessage("Once your application is processed $" + rate + " will be charged to your account.");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean success = submitForm(((EditText)findViewById(R.id.dobField)).getText().toString(), ((EditText)findViewById(R.id.addrField)).getText().toString());
                                if (!success) {
                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(DriversLicense.this);
                                    builder2.setCancelable(false);
                                    builder2.setTitle("ERROR");
                                    builder2.setMessage("An error has occurred while processing your request. Please try again later.");
                                    builder2.setPositiveButton("Ok",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            });


                                    AlertDialog dialog2 = builder2.create();
                                    dialog2.show();
                                } else {
                                    finish();
                                }
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    private boolean submitForm(String dob, String address) {
        try {
            //String firstName = ;
            //String lastName = ;

            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
