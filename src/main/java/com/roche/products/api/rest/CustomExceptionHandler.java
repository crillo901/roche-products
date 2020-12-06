package com.roche.products.api.rest;

import com.roche.products.common.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        logger.error("Unexpected exception", ex);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "unknown");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(IllegalStateException ex) {
        logger.error("IllegalStateException exception", ex);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "not-found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleBadRequestExceptions(IllegalArgumentException ex) {
        logger.error("IllegalArgumentException exception", ex);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "bad-request");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleBadRequestExceptions(EntityNotFoundException ex) {
        logger.error("EntityNotFoundException exception", ex);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "not-found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}