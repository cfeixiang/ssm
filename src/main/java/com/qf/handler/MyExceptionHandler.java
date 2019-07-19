package com.qf.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Administrator 2019/7/17 0017 21:12
 */
@ControllerAdvice
@Component
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String ex(Exception e){
        e.printStackTrace();
        return "error";
    }
}
