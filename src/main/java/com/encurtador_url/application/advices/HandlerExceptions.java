package com.encurtador_url.application.advices;

import com.encurtador_url.domain.dtos.exceptions.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExceptions extends RuntimeException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> errorNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ExceptionDto> errorResourceAccess(ResourceAccessException e) {
        return ResponseEntity.badRequest().body(new ExceptionDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> errorIllegaArg(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ExceptionDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> errorNullPointer(NullPointerException e) {
        return ResponseEntity.badRequest().body(new ExceptionDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ExceptionDto genericException(Exception e) {
        return new ExceptionDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
