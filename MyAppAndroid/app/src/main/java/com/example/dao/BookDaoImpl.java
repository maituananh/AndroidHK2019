package com.example.dao;

import com.example.model.Book;

import java.util.List;

public interface BookDaoImpl {
    List<Book> getAllBook();
    Book findBookById(int id);
    void addNewBook(Book book);
    void updateBookById(Book book);
    void updateQuantityBookById(int quantity, int idBook);
    void deleteBookById(int id);
    List searchNameBook(String name);
    List searchAuthorBook(String name);
}
