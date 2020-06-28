package com.example.dao;

import android.database.Cursor;

import com.example.model.Book;
import com.example.myappandroid.Database;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements BookDaoImpl {
    Database database;

    public BookDao(Database database) {
        this.database = database;
    }

    @Override
    public void addNewBook(Book book) {
        database.insertDataHasImage(book);
    }

    @Override
    public List<Book> getAllBook() {
        Cursor cursor = database.getData("SELECT id, name, image, price, author FROM Book");
        List<Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setImage(cursor.getBlob(2));
            book.setPrice(cursor.getString(3));
            book.setAuthor(cursor.getString(4));
            bookList.add(book);
        }
        return bookList;
    }

    @Override
    public void updateQuantityBookById(int quantity, int idBook) {
        database.queryData("UPDATE Book SET quantity = '" + quantity + "' WHERE id = " + idBook);
    }

    @Override
    public Book findBookById(int id) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE id = " + id);
        Book book = new Book();
        while (cursor.moveToNext()) {
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setImage(cursor.getBlob(2));
            book.setPrice(cursor.getString(3));
            book.setAuthor(cursor.getString(4));
            book.setDescription(cursor.getString(5));
            book.setQuantity(cursor.getString(6));
        }
        return book;
    }

    @Override
    public void updateBookById(Book book) {
        database.UpdateDataHasImage(book);
    }

    @Override
    public void deleteBookById(int id) {
        database.queryData("DELETE FROM Book WHERE id = " + id);
    }

    @Override
    public List searchNameBook(String name) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE name LIKE '%" + name + "%' " +
                "OR name LIKE '%" + name + "' " +
                "OR name LIKE '" + name + "%' " +
                "OR name LIKE '" + name + "'");
        List<Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setImage(cursor.getBlob(2));
            book.setPrice(cursor.getString(3));
            book.setAuthor(cursor.getString(4));
            book.setDescription(cursor.getString(5));
            book.setQuantity(cursor.getString(6));
            bookList.add(book);
        }
        return bookList;
    }

    @Override
    public List searchAuthorBook(String name) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE author LIKE '" + name + "' " +
                "OR author LIKE '%" + name + "%' " +
                "OR author LIKE '%" + name + "' " +
                "OR author LIKE '" + name + "%' " +
                "OR author LIKE ' " + name + " '; ");
        List<Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setImage(cursor.getBlob(2));
            book.setPrice(cursor.getString(3));
            book.setAuthor(cursor.getString(4));
            book.setDescription(cursor.getString(5));
            book.setQuantity(cursor.getString(6));
            bookList.add(book);
        }
        return bookList;
    }
}
