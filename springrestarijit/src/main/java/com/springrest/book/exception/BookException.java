package com.springrest.book.exception;

import org.springframework.stereotype.Component;

@Component
public class BookException extends RuntimeException{

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    private  String errorCode;
    private  String errorMessage;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BookException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BookException() {
    }
}
