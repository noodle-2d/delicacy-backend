package com.delicacycomics.delicacy.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends BaseDelicacyException {

    public NotFoundException(String message, Throwable cause) {
        super(message, cause, NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, NOT_FOUND);
    }

    public NotFoundException(Throwable cause) {
        super(cause, NOT_FOUND);
    }

    public NotFoundException() {
        super(NOT_FOUND);
    }

}
