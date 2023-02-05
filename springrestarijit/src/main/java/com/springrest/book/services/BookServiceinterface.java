package com.springrest.book.services;

import com.springrest.book.entities.Book;
import java.util.List;

public interface BookServiceinterface {
    public List<Book> getBooks();

    public Book getBook(long bookId);

    public Book addBook(Book book);

    public void updateBook(Book book);

    public void deleteBook(long parseLong);

}