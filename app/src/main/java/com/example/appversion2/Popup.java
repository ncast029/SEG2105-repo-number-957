package com.example.appversion2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Popup extends AppCompatActivity {

    private static String res = "Please upload a proof of residence.";
    private static String stat = "Please upload a proof of status.";
    private static String photo = "Please upload a photo of yourself.";
    private static boolean resb = false;
    private static boolean statb = false;
    private static boolean photob = false;

    public static void setresb(boolean b) {
        resb = b;
    }
    public static void setstatb(boolean b) {
        statb = b;
    }
    public static void setphotob(boolean b) {
        photob = b;
    }

    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);

        String docs = "";
        if (resb) docs += res;
        if (statb) docs += " " + stat;
        if (photob) docs += " " + photo;

        ((TextView) findViewById(R.id.textbox)).setText(docs);

    }

}
