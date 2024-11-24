package com.web.flux.advice;

import com.web.flux.services.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata erros de validação
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<String>> handleValidationExceptions(WebExchangeBindException ex) {
        String errors = ex.getFieldErrors().stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .reduce("", (a, b) -> a + "\n" + b);

        return Mono.just(ResponseEntity.badRequest().body(errors));
    }

    // Trata outros erros de entrada
    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ResponseEntity<String>> handleInputExceptions(ServerWebInputException ex) {
        return Mono
                .just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid request: " + ex.getMessage()));
    }

    // Trata erros de dados não encontrados
    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<String>> handleNotFoundExceptions(NotFoundException ex) {
        return Mono
                .just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage()));
    }
}

