package com.example.sosanhhaiso;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int numberOfBut1;
    private int numberOfBut2;
    private static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ranDom2So();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void clickBut1(View view) {
        if (numberOfBut1 > numberOfBut2) {
            score += 1;
        } else {
            score -= 1;
        }
        setTextScore(score);
    }

    public void clickBut2(View view) {
        if (numberOfBut2 > numberOfBut1) {
            score += 1;
        } else {
            score -= 1;
        }
        setTextScore(score);
    }

    public void setTextScore(int score) {
        TextView textView = findViewById(R.id.textRanDom);
        textView.setText("Score = " + score);
        ranDom2So();
    }

    public void ranDom2So() {
        Random rd1 = new Random();
        int number1 = rd1.nextInt(1000);
        Button button1 = findViewById(R.id.but1);
        button1.setText("" + number1);
        numberOfBut1 = number1;

        Random rd2 = new Random();
        int number2 = rd2.nextInt(1000);
        Button button2 = findViewById(R.id.but2);
        button2.setText("" + number2);
        numberOfBut2 = number2;
    }
}
