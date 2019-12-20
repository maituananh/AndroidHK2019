package com.example.myappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dao.BookDao;
import com.example.model.Book;
import com.example.model.KeepInformation;

public class DetailEditBook extends AppCompatActivity {
    Database database;
    BookDao bookDao;

    private int idBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_edit_book);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookDao = new BookDao(database);

        Bundle extras = getIntent().getExtras();
        idBook = extras.getInt("idBook"); // LẤY LẠI ID TỪ HOME LIST BOOK WITH ROLE ADMIN

        TextView nameBook = findViewById(R.id.nameBook);
        TextView nameAuthor = findViewById(R.id.nameAuthor);
        TextView price = findViewById(R.id.price);
        TextView quantity = findViewById(R.id.quantity);
        TextView description = findViewById(R.id.description);
        ImageView imageView = findViewById(R.id.image);

        Book book = bookDao.findBookById(idBook);
        nameBook.setText("Name: " + book.getName());
        nameAuthor.setText("Author: " + book.getAuthor());
        price.setText("Price: " + book.getPrice());
        quantity.setText("Quantity: " + book.getQuantity());
        description.setText("Description: " + book.getDescription());
        // chuyển byte từ data sang bitmap và gắn vào imageView
        byte[] image = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);

    }

    public void onClickEditBook(View view) {
        // chuyển sang form update
        Intent intent = new Intent(DetailEditBook.this, UpdateBook.class);
        intent.putExtra("idBook", idBook);
        startActivity(intent);
    }

    public void onClickDeleteBook(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage("Do you want to DELETE this book?");
        // Nút Ok
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                bookDao.deleteBookById(idBook);
                Intent intent = new Intent(DetailEditBook.this, HomeListBook.class);
                startActivity(intent);
                finish();
            }
        });
        //Nút Cancel
        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
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
