package com.springrest.book.controller;

import com.springrest.book.entities.Book;
import com.springrest.book.exception.BookException;
import com.springrest.book.exception.ControllerException;
import com.springrest.book.logger.BookLogger;
import com.springrest.book.services.BookServiceinterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import java.util.List;


@RestController
public class bookcontroller {
    private static final Logger logger = BookLogger.getLogger(bookcontroller.class);
    @Autowired
    private BookServiceinterface bookService;

    //Get all the books

    @GetMapping("/Books")
    public ResponseEntity <List<Book>> getBooks()

    {
        String method = "getBooks()";
        List <Book> list = bookService.getBooks();
        if(list.isEmpty())
        {
            logger.info("Controller called {}", method);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            logger.info("Controller called {}", method);
            return new  ResponseEntity<List<Book>>(list, HttpStatus.OK);

        }


    //Get book by id
    @GetMapping("/Books/{bookId}")
    public ResponseEntity getBook(@PathVariable String bookId) {
        String method = "singleBook";
        try {
            logger.info("Controller {} Called with {}", method, bookId);
            return new ResponseEntity(bookService.getBook(Long.parseLong(bookId)), HttpStatus.FOUND);
        } catch (BookException bookException) {
            logger.error("Controller {} Called with {}", method, bookId);
            return new ResponseEntity(bookException.getErrorMessage(), HttpStatus.CONFLICT);
        }
    }

    //Add a book
    @PostMapping("/Books")
    public ResponseEntity<?>addBook(@RequestBody Book book) {
        String method = "addBook";
        try {
            logger.info("Control Called {} with {}", method, book);
            return new ResponseEntity<Book>(this.bookService.addBook(book), HttpStatus.ACCEPTED);
        } catch (BookException e) {
            logger.error("Control Called {} with {}", method, book);
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            logger.error("Control Called {} with {}", method, book);
            ControllerException ce = new ControllerException("400","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    //Update a book using PUT
    @PutMapping("/Books")
    public ResponseEntity <Book> updateBook(@RequestBody Book book){
        String method = "updateBook";
        try{
            logger.info("Control Called {} with {}", method, book);
            bookService.updateBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Delete a book
    @DeleteMapping("/Books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable String bookId) {
        try{
            this.bookService.deleteBook(Long.parseLong(bookId));
            return new ResponseEntity("Delete Successfull",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

