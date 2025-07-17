package com.livraria_api.handlers;

import com.livraria_api.exception.ConflictException;
import com.livraria_api.exception.ForbbidenException;
import com.livraria_api.exception.NotFoundException;
import com.livraria_api.utils.MessageRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.NotActiveException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageRest> handlerNotFoundException(NotFoundException ex){
        MessageRest message = new MessageRest(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<MessageRest> handlerPSQLException(SQLException ex){
        MessageRest messageRest = new MessageRest(ex.getMessage());
        if(ex.getSQLState().equals("23503")){
            messageRest.setMessage("O registro não pode ser deletado");
            messageRest.setHttpStatus(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(messageRest.getHttpStatus()).body(messageRest);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<MessageRest> handlerConflictException(ConflictException ex){
        MessageRest message = new MessageRest(ex.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(ForbbidenException.class)
    public ResponseEntity<MessageRest> handlerForbbidenException(ForbbidenException ex){
        MessageRest message = new MessageRest(ex.getMessage(), HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }
}
