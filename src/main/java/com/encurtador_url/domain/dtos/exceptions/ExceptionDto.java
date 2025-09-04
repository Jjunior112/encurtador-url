package com.encurtador_url.domain.dtos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExceptionDto(LocalDateTime timestamp , int statusCode, String message) {
}
