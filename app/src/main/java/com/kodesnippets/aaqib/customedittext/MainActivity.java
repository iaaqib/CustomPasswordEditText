package com.kodesnippets.aaqib.customedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kodesnippets.aaqib.custompasswordedittext.CustomPasswordEditText;


public class MainActivity extends AppCompatActivity {

    CustomPasswordEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordEditText = (CustomPasswordEditText) findViewById(R.id.custom_pass);
        passwordEditText.setHint("Password");
        passwordEditText.setText("Password112");

    }
}
