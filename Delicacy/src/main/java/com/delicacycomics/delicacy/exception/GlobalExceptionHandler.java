package com.delicacycomics.delicacy.exception;

import com.delicacycomics.delicacy.dto.request.ErrorDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseDelicacyException.class)
    @ResponseBody
    public ErrorDto handleBaseDelicacyException(BaseDelicacyException exception,
                                                HttpServletResponse response) {
        response.setStatus(exception.getHttpStatus().value());
        return new ErrorDto(exception.getMessage(), exception.getHttpStatus().value());
    }

}
