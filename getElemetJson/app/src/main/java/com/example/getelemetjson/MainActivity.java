package com.example.getelemetjson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getButton = findViewById(R.id.button);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity.class);
                EditText urlEditText = findViewById(R.id.edittext);
                String url  = urlEditText.getText().toString();
                intent.putExtra("album", url);
                startActivity(intent);
            }
        });
    }
}
