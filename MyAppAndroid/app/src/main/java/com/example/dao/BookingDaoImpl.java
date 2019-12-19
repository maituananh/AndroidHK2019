package com.example.dao;

import java.util.List;

public interface BookingDaoImpl {
    void userBookingBook(int idUserBooking, int idBookBooking);
    List getAllBookOfUserBooking(int idUser_Booking);
    void deleteBookingById(int id);
}
