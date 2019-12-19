package com.example.myappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dao.BookDao;
import com.example.dao.BookingDao;
import com.example.model.Booking;
import com.example.model.KeepInformation;

import java.util.ArrayList;
import java.util.List;

public class BookingListOfUser extends AppCompatActivity {
    Database database;
    BookingDao bookingDao;
    BookDao bookDao;

    ListView listView;
    List<Booking> bookArrayList;
    ListBookingOfUserAdapter listBookingOfUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_booking_of_user);
        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookingDao = new BookingDao(database);
        bookDao = new BookDao(database);

        mapping();
        listBookingOfUserAdapter = new ListBookingOfUserAdapter(this, R.layout.list_book_of_user_booking, bookArrayList);
        listView.setAdapter(listBookingOfUserAdapter);

        // sự kiện trả lại sách
        final AlertDialog.Builder b = new AlertDialog.Builder(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                final int idBook = bookArrayList.get(position).getBook().getId();
                final int idBooking = bookArrayList.get(position).getId();
                final int quantity = Integer.parseInt(bookArrayList.get(position).getBook().getQuantity());
                b.setMessage("Bạn có muốn trả sách không?");
                // Nút Ok
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        bookDao.updateQuantityBookById(quantity + 1, idBook);
                        bookingDao.deleteBookingById(idBooking);
                        Intent intent = new Intent(BookingListOfUser.this, BookingListOfUser.class);
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
        });
    }

    public void mapping() {
        listView = findViewById(R.id.listBookOfUserBooking);
        bookArrayList = new ArrayList<>();
        bookingDao = new BookingDao(database);
        bookArrayList = bookingDao.getAllBookOfUserBooking(KeepInformation.getIdUser());
        if (bookArrayList.size() == 0) {
            Toast.makeText(this, "Bạn chưa thuê cuốn sách nào", Toast.LENGTH_SHORT).show();
        }
    }

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
