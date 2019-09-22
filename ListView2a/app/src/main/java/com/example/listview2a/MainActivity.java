package com.example.listview2a;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addListView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addListView() {
        listView = findViewById(R.id.listViewQuocGia);
        stringArrayList = new ArrayList<>();
        stringArrayList.add("VIET NAM");
        stringArrayList.add("THAI LAN");
        stringArrayList.add("TRUNG QUOC");
        stringArrayList.add("LAO");
        stringArrayList.add("TRIEU TIEN");
        stringArrayList.add("USA");
        stringArrayList.add("ANH");
        stringArrayList.add("NHAT BAN");
        stringArrayList.add("HAN QUOC");
        stringArrayList.add("THUY DIEN");
        stringArrayList.add("MONG CO");
        stringArrayList.add("SINGAPO");

        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,
                stringArrayList);
        listView.setAdapter(arrayAdapter);
    }
}
