package com.example.demo6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            return;
        }
        GoNextFragment goNextFragment = new GoNextFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(R.id.);
    }
}
