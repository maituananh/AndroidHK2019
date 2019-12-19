package com.example.myappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dao.BookDao;
import com.example.dao.BookingDao;
import com.example.dao.UserDao;
import com.example.model.Book;
import com.example.model.Booking;
import com.example.model.KeepInformation;

import java.util.List;

public class DetailBook extends AppCompatActivity {
    Database database;
    BookDao bookDao;
    BookingDao bookingDao;
    UserDao userDao;

    private Book book;
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
        bookingDao = new BookingDao(database);

        book = bookDao.findBookById(idBook);
        this.idBook = book.getId();
        TextView nameBook = findViewById(R.id.nameBook);
        TextView nameAuthor = findViewById(R.id.nameAuthor);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);
        TextView quantity = findViewById(R.id.quantity);
        ImageView imageView = findViewById(R.id.image);
        nameBook.setText("Name: " + book.getName());
        nameAuthor.setText("Author: " + book.getAuthor());
        price.setText("Name: " + book.getPrice());
        description.setText("Description: " + book.getDescription());
        quantity.setText(book.getQuantity());
        imageView.setImageResource(getImageId(this, book.getImage()));
    }

    public void functionBooking(View view) {
        int idUser = KeepInformation.getIdUser();
        List<Booking> listBookingOfUser = bookingDao.getAllBookOfUserBooking(idUser);
        boolean checkBooking = false;
        int quantityBook = 0;
        for (int i = 0; i < listBookingOfUser.size(); i++) {
            if (idBook == listBookingOfUser.get(i).getBook().getId()) {
                checkBooking = true; // check sách đã có thì ko được thuê nữa
                quantityBook = Integer.parseInt(listBookingOfUser.get(i).getBook().getQuantity()); // nếu sl sách = 0 thì ko thuê đc
            }
        }
        if (checkBooking) {
            // nếu sách đã thuê rồi
            Toast.makeText(this, "Bạn đã thuê sách này rồi ạ !", Toast.LENGTH_SHORT).show();
        } else {
            if (quantityBook == 0) {
                Toast.makeText(this, "Đã hết sách !", Toast.LENGTH_SHORT).show();
            } else {
                // nếu sách chưa thuê
                bookingDao.userBookingBook(idUser, idBook);
                bookDao.updateQuantityBookById(Integer.parseInt(book.getQuantity()) - 1, idBook);
                Intent intent = new Intent(DetailBook.this, BookingListOfUser.class);
                startActivity(intent);
            }
        }
    }

    // lấy id image
    public static int getImageId(Context context, String imageName) {
        imageName = imageName.substring(0, imageName.indexOf("."));
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
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
