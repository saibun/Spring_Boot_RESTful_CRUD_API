package com.springrest.book.logger;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class BookLogger {
    public static Logger getLogger(Class c) {
        return LoggerFactory.getLogger(c);
    }
}