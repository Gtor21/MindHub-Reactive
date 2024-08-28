package com.mindhub.user_service.exeptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public Mono<ResponseEntity<String>> handlerNotFundException(ChangeSetPersister.NotFoundException ex ){
        return Mono.just( ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }
}
