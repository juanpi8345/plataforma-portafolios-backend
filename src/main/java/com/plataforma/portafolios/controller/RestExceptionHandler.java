package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.dto.ErrorMessage;
import com.plataforma.portafolios.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> entityNotFound(EntityNotFoundException e){
        ErrorMessage err = new ErrorMessage(HttpStatus.NOT_FOUND,e.getMessage().toUpperCase());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(EntitiesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> entitiesNotFound(EntitiesNotFoundException e){
        ErrorMessage err = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage().toUpperCase());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> badCredentials(BadCredentialsException e){
        ErrorMessage err = new ErrorMessage(HttpStatus.UNAUTHORIZED,e.getMessage().toUpperCase());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ExceptionHandler(UserNotLoggedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> userNotLogged(UserNotLoggedException e){
        ErrorMessage err = new ErrorMessage(HttpStatus.UNAUTHORIZED,e.getMessage().toUpperCase());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> entityAlreadyExists(EntityAlreadyExists e){
        ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST,e.getMessage().toUpperCase());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InvalidEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> InvalidEntity(InvalidEntityException e){
        ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST,e.getMessage().toUpperCase());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
