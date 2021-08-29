package com.simpleauth.error;

import com.simpleauth.error.exception.InvalidConfirmPasswordException;
import com.simpleauth.error.exception.PasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(PasswordException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidConfirmPasswordException(PasswordException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.from(errorCode), HttpStatus.valueOf(errorCode.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_ERROR;
        return new ResponseEntity<>(ErrorResponse.from(errorCode), HttpStatus.valueOf(errorCode.getStatusCode()));
    }
}
