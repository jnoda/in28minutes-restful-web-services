package com.julionoda.in28minutes.rest.webservices.exception;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ExceptionResponse {
    LocalDateTime timestamp;
    String message;
    String details;
}
