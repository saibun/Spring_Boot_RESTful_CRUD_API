package com.Rest_Example.CourseList.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//for handling logger into project
public class GlobalUse {

    public static Logger getLogger(Class c){
        return LoggerFactory.getLogger(c);
    }
}
