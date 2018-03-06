package com.delicacycomics.delicacy.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalServerErrorException extends BaseDelicacyException {

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause, INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message) {
        super(message, INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause, INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException() {
        super(INTERNAL_SERVER_ERROR);
    }

}
