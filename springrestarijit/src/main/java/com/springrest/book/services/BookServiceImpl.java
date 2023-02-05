package com.springrest.book.services;

import com.springrest.book.dao.BookDao;
import com.springrest.book.entities.Book;
import com.springrest.book.exception.BookException;
import com.springrest.book.logger.BookLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

    @Service
    public class BookServiceImpl implements BookServiceinterface {
        private final Logger serviceImplLogger = BookLogger.getLogger(BookServiceImpl.class);

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> getBooks() {
        List<Book> bookList = null;
        try {
            bookList = bookDao.findAll();
        }
        catch (Exception e) {
            throw  new BookException("605", "Something went wrong in Service layer while fetching all books" + e.getMessage());
        }
        if (bookList.isEmpty())
            throw new BookException("604", "The list completely empty, there is have nothing to return");
        String method = "getBooks";
       serviceImplLogger.info("ServiceImpl Called {}",method);
        return bookList;
    }



    @Override
    public Book getBook(long bookId) {
        try {
            String method = "getBookId";
            serviceImplLogger.info("ServiceImpl Called {} with {}",method,bookId);
            return bookDao.findById(bookId).get();
        } catch (IllegalArgumentException e) {
            throw new BookException("606", "given book id is null, send valid book id");
        } catch (java.util.NoSuchElementException e) {
            throw new BookException("406","given book id doesn't exist " + e.getMessage());
        } catch (Exception e) {
            throw new BookException("609","Something went wrong while fetching all books" + e.getMessage());
        }
    }

    @Override
    public Book addBook(Book book) {
        if (book.getName().isEmpty() || book.getName().length() == 0){
                throw new BookException("406", "Please send proper book name, It is empty");
            }
        try {
                String method = "addBook";
            serviceImplLogger.info("ServiceImpl Called {}", method);
            bookDao.save(book);
            return book;
        } catch (IllegalArgumentException e) {
            throw new BookException("602", "given book is null" + e.getMessage());
        } catch (Exception e) {
            throw new BookException("603", "Something went wrong in Service layer while saving the book name" + e.getMessage());
        }
    }

    @Override
    public void updateBook(Book book) {
            if (book.getGenre().isEmpty() || book.getGenre().length() == 0){
                throw new BookException("610","Please send proper genre, It is blank");
            }
            try {
                String method = "updateBook";
                serviceImplLogger.info("ServiceImpl Called {}",method);
                bookDao.save(book);
            } catch (IllegalArgumentException e) {
                throw new BookException("611","given book already exists" + e.getMessage());
        }
    }


    @Override
    public void deleteBook(long parseLong) {
        try {
            bookDao.deleteById(parseLong);
        } catch (IllegalArgumentException e) {
            throw new BookException("608","given book id is null, please send some valid id" + e.getMessage());
        } catch (Exception e) {
            throw  new BookException("608","Something went wrong while fetching all books" + e.getMessage());
        }
        String method = "deleteBook";
        serviceImplLogger.info("ServiceImpl Called {}",method);
        Book entity = bookDao.findById(parseLong).get();
        bookDao.delete(entity);
    }
}




