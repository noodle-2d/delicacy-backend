package com.delicacycomics.delicacy.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class ForbiddenException extends BaseDelicacyException {

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause, FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message, FORBIDDEN);
    }

    public ForbiddenException(Throwable cause) {
        super(cause, FORBIDDEN);
    }

    public ForbiddenException() {
        super(FORBIDDEN);
    }

}
