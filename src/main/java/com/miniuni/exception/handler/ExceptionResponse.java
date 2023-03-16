package com.miniuni.exception.handler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private final String message;

    private final LocalDateTime timeStamp;

    private final HttpStatus httpStatus;
}
