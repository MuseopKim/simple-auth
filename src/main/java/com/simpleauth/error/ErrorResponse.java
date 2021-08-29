package com.simpleauth.error;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int statusCode;
    private final String errorMessage;

    private ErrorResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatusCode(), errorCode.getMessage());
    }
}
