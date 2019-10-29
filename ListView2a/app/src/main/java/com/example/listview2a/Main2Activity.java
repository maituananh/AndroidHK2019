package com.example.listview2a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent2 = getIntent();
        String countryStr = intent2.getStringExtra("countryName");
        TextView textView = findViewById(R.id.nameCountry);
        TextView textView1 = findViewById(R.id.describe);
        textView.setText(countryStr);
        textView1.setText(countryStr + " DEP KINH DAU");
    }
}
