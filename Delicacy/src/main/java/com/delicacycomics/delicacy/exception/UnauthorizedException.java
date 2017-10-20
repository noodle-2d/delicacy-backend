package com.delicacycomics.delicacy.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class UnauthorizedException extends BaseDelicacyException {

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause, UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
        super(message, UNAUTHORIZED);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause, UNAUTHORIZED);
    }

    public UnauthorizedException() {
        super(UNAUTHORIZED);
    }

}
