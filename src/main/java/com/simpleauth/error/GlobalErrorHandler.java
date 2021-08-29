package com.simpleauth.error;

import com.simpleauth.error.exception.AuthException;
import com.simpleauth.error.exception.EntityNotFoundException;
import com.simpleauth.error.exception.PasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(AuthException.class)
    protected ResponseEntity<ErrorResponse> handleAuthException(AuthException exception) {
        log.error("handleAuthException", exception);
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.from(errorCode), HttpStatus.valueOf(errorCode.getStatusCode()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error("handleEntityNotFoundException", exception);
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.from(errorCode), HttpStatus.valueOf(errorCode.getStatusCode()));
    }

    @ExceptionHandler(PasswordException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidConfirmPasswordException(PasswordException exception) {
        log.error("handlePasswordException", exception);
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.from(errorCode), HttpStatus.valueOf(errorCode.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("handleMethodArgumentNotValidException", exception);
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_ERROR;
        return new ResponseEntity<>(ErrorResponse.from(errorCode), HttpStatus.valueOf(errorCode.getStatusCode()));
    }
}
