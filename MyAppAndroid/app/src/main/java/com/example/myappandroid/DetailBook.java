package com.example.myappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dao.BookDao;
import com.example.dao.UserDao;
import com.example.model.Book;
import com.example.model.Booking;

public class DetailBook extends AppCompatActivity {
    Database database;
    BookDao bookDao;

    UserDao userDao;

    private int idBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        Bundle extras = getIntent().getExtras();
        int idBook = extras.getInt("idBook"); // LẤY LẠI ID TỪ HOME LIST BOOK

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookDao = new BookDao(database);
        userDao = new UserDao(database);


        Book book = bookDao.findBookById(idBook);
        this.idBook = book.getId();
        TextView nameBook = findViewById(R.id.nameBook);
        TextView nameAuthor = findViewById(R.id.nameAuthor);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);
        ImageView imageView = findViewById(R.id.imageView);
        nameBook.setText("Name: " + book.getName());
        nameAuthor.setText("Author: " + book.getAuthor());
        price.setText("Name: " + book.getPrice());
        description.setText("Description: " + book.getDescription());

        imageView.setImageResource(getImageId(this, book.getImage()));
    }

    public void booking(View view) {
        // lấy id của sách
        // lấy id của user
        // tạo mới booking dao
        
    }

    public static int getImageId(Context context, String imageName) {
        imageName = imageName.substring(0, imageName.indexOf("."));
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
