package com.delicacycomics.delicacy.exception;

import org.springframework.http.HttpStatus;

public class BaseDelicacyException extends RuntimeException {

    private HttpStatus httpStatus;

    public BaseDelicacyException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public BaseDelicacyException(String message, HttpStatus httpStatus) {
        this(message, null, httpStatus);
    }

    public BaseDelicacyException(Throwable cause, HttpStatus httpStatus) {
        this(null, cause, httpStatus);
    }

    public BaseDelicacyException(HttpStatus httpStatus) {
        this(null, null, httpStatus);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
