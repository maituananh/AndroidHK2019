package com.example.myappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dao.BookDao;
import com.example.model.Book;

import java.util.ArrayList;
import java.util.List;

public class HomeListBook extends AppCompatActivity {
    ListView listView;
    List<Book> bookArrayList;
    ListBookAdapter listBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list_book);
        mapping();
        listBookAdapter = new ListBookAdapter(this, R.layout.list_book, bookArrayList);
        listView.setAdapter(listBookAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                int idItem = bookArrayList.get(position).getId();
                Intent intent = new Intent(HomeListBook.this, DetailBook.class);
                intent.putExtra("idBook", idItem);
                startActivity(intent);
            }
        });
    }

    public void mapping() {
        listView = findViewById(R.id.listBook);
        bookArrayList = new ArrayList<>();
        Database database = new Database(this, "ManagementBook.sqlite", null, 1);
        BookDao bookDao = new BookDao(database);
        bookArrayList = bookDao.getAllBook();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu_user, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
