package com.mmn.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignErrorException.class)
    public String handleFeignErrorException(
            final FeignErrorException e,
            final HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        return e.getBody();
    }

}
