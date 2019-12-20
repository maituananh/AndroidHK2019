package com.example.dao;

import android.database.Cursor;

import com.example.model.Book;
import com.example.model.Booking;
import com.example.model.User;
import com.example.myappandroid.Database;

import java.util.ArrayList;
import java.util.List;

public class BookingDao implements BookingDaoImpl{
    Database database;

    public BookingDao (Database database) {
        this.database = database;
    }

    @Override
    public void deleteBookingById(int id) {
        database.queryData("DELETE FROM Booking WHERE id = + " + id);
    }

    @Override
    public List getAllBookOfUserBooking(int idUserBooking) {
        Cursor cursor = database.getData("SELECT Booking.id, User.id, Book.id, Book.name, Book.image, Book.price, Book.quantity" +
                " FROM User INNER JOIN Booking ON Booking.idUser_Booking = User.id " +
                "INNER JOIN Book ON Book.id = Booking.idBook_Booking WHERE User.id = " + idUserBooking);
        List<Booking> list = new ArrayList();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(1));

            Book book = new Book();
            book.setId(cursor.getInt(2));
            book.setName(cursor.getString(3));
            book.setImage(cursor.getBlob(4));
            book.setPrice(cursor.getString(5));
            book.setQuantity(cursor.getString(6));

            Booking booking = new Booking();
            booking.setId(cursor.getInt(0));
            booking.setUser(user);
            booking.setBook(book);

            list.add(booking);
        }
        return list;
    }

    @Override
    public void userBookingBook(int idUserBooking, int idBookBooking) {
        database.queryData("INSERT INTO Booking VALUES(NULL, '" + idUserBooking + "', '" + idBookBooking + "')");
    }
}
