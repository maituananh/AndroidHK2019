package com.example.demo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void action(View view) {
        ImageView imageView = findViewById(R.id.i);
        switch(view.getId()){
            case R.id.hinh1:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ihinh1));
                Switch(view);
                break;

            case R.id.hinh2:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ihinh4));
                Switch(view);
                break;

            case R.id.hinh3:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ihinh3));
                Switch(view);
                break;

            case R.id.hinh4:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ihinh2));
                Switch(view);
                break;
        }
    }
    public void Switch(View view) {
        Switch aSwitch = findViewById(R.id.switch1);
        ImageView imageView = findViewById(R.id.i);
        Boolean aBoolean = aSwitch.isChecked();
        if (aBoolean) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
