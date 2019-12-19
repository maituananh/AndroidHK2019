package com.example.myappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UpdateBook extends AppCompatActivity {
    private int idBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        Bundle extras = getIntent().getExtras();
        idBook = extras.getInt("idBook"); // LẤY LẠI ID TỪ Detail edit book

    }


}
