package com.example.myappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dao.BookDao;
import com.example.model.Book;
import com.example.model.KeepInformation;

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
                if (KeepInformation.getRole().trim().toUpperCase().equals("ADMIN")) {
                    Intent intent = new Intent(HomeListBook.this, DetailEditBook.class);
                    intent.putExtra("idBook", idItem);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(HomeListBook.this, DetailBook.class);
                    intent.putExtra("idBook", idItem);
                    startActivity(intent);
                }
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

    // sử lý menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (KeepInformation.getRole().toUpperCase().equals("ADMIN")) {
            getMenuInflater().inflate(R.menu.layout_menu_admin, menu);
        } else {
            getMenuInflater().inflate(R.menu.layout_menu_user, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if (KeepInformation.getRole().toUpperCase().equals("ADMIN")) {
            switch (item.getItemId()) {
                case R.id.addBookAdmin:
                    intent = new Intent(this, AddBook.class);
                    startActivity(intent);
                    break;
                case R.id.listBookAdmin:
                    intent = new Intent(this, HomeListBook.class);
                    startActivity(intent);
                    break;
                case R.id.searchBookAdmin:
                    intent = new Intent(this, SearchBook.class);
                    startActivity(intent);
                    break;
                case R.id.bookBookedAdmin:
                    intent = new Intent(this, BookingListOfUser.class);
                    startActivity(intent);
                    break;
                case R.id.logoutAdmin:
                    KeepInformation.setIdUser(0);
                    KeepInformation.setRole("");
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        } else {
            switch (item.getItemId()) {
                case R.id.listBookUser:
                    intent = new Intent(this, HomeListBook.class);
                    startActivity(intent);
                    break;
                case R.id.searchBookUser:
                    intent = new Intent(this, SearchBook.class);
                    startActivity(intent);
                    break;
                case R.id.bookBookedUser:
                    intent = new Intent(this, BookingListOfUser.class);
                    startActivity(intent);
                    break;
                case R.id.logoutUser:
                    KeepInformation.setIdUser(0);
                    KeepInformation.setRole("");
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
