package com.alves.vitor.DigitalAccounts.infra.controller.handler;

import com.alves.vitor.DigitalAccounts.domain.exceptions.AccountNotFoundException;
import com.alves.vitor.DigitalAccounts.domain.exceptions.CpfNotUniqueException;
import com.alves.vitor.DigitalAccounts.domain.exceptions.InvalidDataException;
import com.alves.vitor.DigitalAccounts.domain.exceptions.PersonNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    ResponseEntity<?> defaultHandler(RuntimeException ex) {
        Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Bad Request",
                "message", ex.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<?> handleInvalidDataException(InvalidDataException ex) {
        return defaultHandler(ex);
    }

    @ExceptionHandler(CpfNotUniqueException.class)
    public ResponseEntity<?> handleCpfNotUniqueException(CpfNotUniqueException ex) {
        return defaultHandler(ex);

    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFoundException(AccountNotFoundException ex) {
        return defaultHandler(ex);

    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<?> handlePersonNotFoundException(PersonNotFoundException ex) {
        return defaultHandler(ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return defaultHandler(ex);
    }
}
