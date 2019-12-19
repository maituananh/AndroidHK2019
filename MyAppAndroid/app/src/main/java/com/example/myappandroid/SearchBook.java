package com.example.myappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dao.BookDao;
import com.example.model.Book;
import com.example.model.KeepInformation;

import java.util.ArrayList;
import java.util.List;

public class SearchBook extends AppCompatActivity {
    ListView listView;
    List<Book> bookArrayList;
    ListBookAdapter listBookAdapter;
    Database database;
    BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookDao = new BookDao(database);
    }

    public void mappingData() {
        listBookAdapter = new ListBookAdapter(this, R.layout.list_book, bookArrayList);
        if (bookArrayList.size() > 0) {
            listView.setAdapter(listBookAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    int idItem = bookArrayList.get(position).getId();
                    Intent intent = new Intent(SearchBook.this, DetailBook.class);
                    intent.putExtra("idBook", idItem);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(this, "Không có thông tin bạn cần tìm", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSearchByName(View view) {
        EditText editText = findViewById(R.id.searchNameBook);
        listView = findViewById(R.id.listViewSearchBook);
        bookArrayList = new ArrayList<>();
        bookArrayList = bookDao.searchNameBook(editText.getText().toString().trim());
        mappingData();
    }

    public void onClickSearchByNameAuthor(View view) {
        EditText editText = findViewById(R.id.searchNameAuthorBook);
        listView = findViewById(R.id.listViewSearchBook);
        bookArrayList = new ArrayList<>();
        bookArrayList = bookDao.searchAuthorBook(editText.getText().toString().trim());
        mappingData();
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
