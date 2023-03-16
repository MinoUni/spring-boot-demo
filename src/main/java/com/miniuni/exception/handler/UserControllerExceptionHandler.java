package com.miniuni.exception.handler;

import com.miniuni.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class UserControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        ExceptionResponse excResponse = new ExceptionResponse(
                e.getMessage(),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND);
        log.info(excResponse.getHttpStatus().toString() + " " + excResponse.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excResponse);
    }
}
