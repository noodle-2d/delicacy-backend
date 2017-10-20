package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.exception.BaseDelicacyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseDelicacyException.class)
    public ResponseEntity handleBaseDelicacyException(BaseDelicacyException exception) {
        return new ResponseEntity(exception.getHttpStatus());
    }

}
