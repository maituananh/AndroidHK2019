package com.example.listview2a;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private int i = -1;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String country = stringArrayList.get(i);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("countryName", country);
                startActivity(intent);
            }
        });
    }

    public void addListView() {
        listView = findViewById(R.id.listViewQuocGia);
        stringArrayList.add(tangID() + " - VIET NAM");
        stringArrayList.add(tangID() + " - THAI LAN");
        stringArrayList.add(tangID() + " - TRUNG QUOC");
        stringArrayList.add(tangID() + " - LAO");
        stringArrayList.add(tangID() + " - TRIEU TIEN");
        stringArrayList.add(tangID() + " - USA");
        stringArrayList.add(tangID() + " - ANH");
        stringArrayList.add(tangID() + " - NHAT BAN");
        stringArrayList.add(tangID() + " - HAN QUOC");
        stringArrayList.add(tangID() + " - THUY DIEN");
        stringArrayList.add(tangID() + " - MONG CO");
        stringArrayList.add(tangID() + " - SINGAPO");
        stringArrayList.add(tangID() + " - AO");
        addAdapter();
    }

    public void actionADD(View view) {
        EditText editTextADD = findViewById(R.id.textADD);
        String add = String.valueOf(editTextADD.getText());
        stringArrayList.add(tangID() +" - "+ add);
        addAdapter();
        editTextADD.setText("");
    }

    public void actionEdit(View view) {
        EditText editTextID = findViewById(R.id.textIDEDIT);
        EditText editTextEdit = findViewById(R.id.textEDIT);
        String id = String.valueOf(editTextID.getText());
        String textEdit = String.valueOf(editTextEdit.getText());
        stringArrayList.set(Integer.parseInt(id.trim()), id + " - " + textEdit);
        addAdapter();
        editTextID.setText("");
        editTextEdit.setText("");
    }

    public void addAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,
                stringArrayList);
        listView.setAdapter(arrayAdapter);
    }

    public int tangID() {
        this.i += 1;
        return this.i;
    }
}
