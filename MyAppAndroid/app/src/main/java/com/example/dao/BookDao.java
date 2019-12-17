package com.example.dao;

import android.database.Cursor;

import com.example.model.Book;
import com.example.myappandroid.Database;
import com.example.myappandroid.R;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements BookDaoImpl{
    Database database;

    public BookDao(Database database) {
        this.database = database;
    }

    @Override
    public void addNewBook(Book book) {
        database.queryData("INSERT INTO Book VALUES(NULL, '"+ book.getName() +"', " +
                "'"+ book.getImage() +"', '"+ book.getPrice() +"', '"+ book.getAuthor() +"', " +
                "'"+ book.getDescription() +"', '"+ book.getQuantity() +"')");
    }

    @Override
    public List<Book> getAllBook() {
        Cursor cursor = database.getData("SELECT * FROM Book");
        List<Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setImage(cursor.getString(2));
            book.setPrice(cursor.getString(3));
            book.setAuthor(cursor.getString(4));
            book.setDescription(cursor.getString(5));
            book.setQuantity(cursor.getString(6));
            bookList.add(book);
        }
        return bookList;
    }

    @Override
    public Book findBookById(int id) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE id = " + id);
        Book book = new Book();
        while (cursor.moveToNext()) {
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setImage(cursor.getString(2));
            book.setPrice(cursor.getString(3));
            book.setAuthor(cursor.getString(4));
            book.setDescription(cursor.getString(5));
            book.setQuantity(cursor.getString(6));
        }
        return book;
    }

    @Override
    public void updateBookById(Book book) {

    }

    @Override
    public void deleteBookById(int id) {
        database.queryData("DELETE FROM Book WHERE id = " + id);
    }

    @Override
    public List searchNameBook(String name) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE name = '" + name + "'");
        List<Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setImage(cursor.getString(2));
            book.setPrice(cursor.getString(3));
            book.setAuthor(cursor.getString(4));
            book.setDescription(cursor.getString(5));
            book.setQuantity(cursor.getString(6));
        }
        return bookList;
    }

    @Override
    public List searchAuthorBook(String name) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE author = '" + name + "'");
        List<Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setImage(cursor.getString(2));
            book.setPrice(cursor.getString(3));
            book.setAuthor(cursor.getString(4));
            book.setDescription(cursor.getString(5));
            book.setQuantity(cursor.getString(6));
        }
        return bookList;
    }
}
