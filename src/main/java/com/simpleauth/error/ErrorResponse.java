package com.simpleauth.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

@Getter
public class ErrorResponse {

    private final int statusCode;
    private final String errorMessage;
    private final List<ValueError> errors;

    private ErrorResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.errors = Collections.emptyList();
    }

    private ErrorResponse(int statusCode, String errorMessage, List<ValueError> errors) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatusCode(), errorCode.getMessage());
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorResponse(errorCode.getStatusCode(), errorCode.getMessage(), ValueError.from(bindingResult));
    }
}
