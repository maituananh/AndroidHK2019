package com.example.myappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dao.BookDao;
import com.example.dao.UserDao;
import com.example.util.SentenceInsertBook;

public class MainActivity extends AppCompatActivity {
    Database database;
    BookDao bookDao;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "ManagementBook.sqlite", null, 1);

        bookDao = new BookDao(database);
        userDao = new UserDao(database);

        database.queryData("DROP TABLE Book");
        database.queryData("DROP TABLE User");
        database.queryData("DROP TABLE Booking");

        database.queryData("CREATE TABLE IF NOT EXISTS Book (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), " +
                "image VARCHAR(200), price VARCHAR(100), author VARCHAR(200), description VARCHAR(200), quantity INTEGER(100))");
        database.queryData("CREATE TABLE IF NOT EXISTS User (id INTEGER PRIMARY KEY AUTOINCREMENT, fullName VARCHAR(200), " +
                "role VARCHAR(20), userName VARCHAR(100), password VARCHAR(100))");
        database.queryData("CREATE TABLE IF NOT EXISTS Booking (id INTEGER PRIMARY KEY AUTOINCREMENT, idUser_Booking INTEGER(100), " +
                "idBook_Booking INTEGER(100))");

        database.queryData("INSERT INTO User VALUES (NULL, 'Mai Tuấn Anh', 'admin', 'admin', 'admin')");
        database.queryData("INSERT INTO User VALUES (NULL, 'Nguyễn Văn A', 'user', 'nguyenvana', '123')");

        // thêm sách vào database
        database.queryData(SentenceInsertBook.book1());
        database.queryData(SentenceInsertBook.book2());
        database.queryData(SentenceInsertBook.book3());
        database.queryData(SentenceInsertBook.book4());
        database.queryData(SentenceInsertBook.book5());
        database.queryData(SentenceInsertBook.book6());
        database.queryData(SentenceInsertBook.book7());
        database.queryData(SentenceInsertBook.book8());
        database.queryData(SentenceInsertBook.book9());
        database.queryData(SentenceInsertBook.book10());

        database.queryData("INSERT INTO Booking VALUES (NULL, 1, 1)");
        database.queryData("INSERT INTO Booking VALUES (NULL, 1, 2)");
        database.queryData("INSERT INTO Booking VALUES (NULL, 2, 2)");
        database.queryData("INSERT INTO Booking VALUES (NULL, 2, 3)");
    }

    public void login(View view) {
        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        String us = userName.getText().toString();
        String pass = password.getText().toString();
        boolean checkUser = userDao.checkUserByUserNameAndPassword(us, pass);
        if (checkUser) {
            // thành công thì chuyển sang trang home
            Intent intent = new Intent(MainActivity.this, HomeListBook.class);
            startActivity(intent);
        } else {
            // thất bại
            Toast.makeText(this, "Wrong username and password", Toast.LENGTH_SHORT).show();
        }
    }
}
