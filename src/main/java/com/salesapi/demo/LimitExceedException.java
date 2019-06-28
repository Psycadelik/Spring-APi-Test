package com.salesapi.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class LimitExceedException extends RuntimeException{
    public LimitExceedException(String message){
        super(message);
    }

}
