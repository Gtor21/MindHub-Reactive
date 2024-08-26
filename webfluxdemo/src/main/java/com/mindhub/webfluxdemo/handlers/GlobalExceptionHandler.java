package com.mindhub.webfluxdemo.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public Mono<ResponseEntity<String>>handlerNotFundException(NotFoundException ex ){
        return Mono.just( ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }
}
