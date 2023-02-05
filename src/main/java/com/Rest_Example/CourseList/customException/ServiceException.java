package com.Rest_Example.CourseList.customException;

import org.springframework.stereotype.Component;

@Component
public class ServiceException extends RuntimeException{


    private String error_code;
    private String error_des;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public ServiceException(String error_code,String error_des){
        super();
        this.error_code=error_code;
        this.error_des=error_des;
    }




    public String getError_des() {
        return error_des;
    }

    public void setError_des(String error_des) {
        this.error_des = error_des;
    }
    public ServiceException(){

    }
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }


}
