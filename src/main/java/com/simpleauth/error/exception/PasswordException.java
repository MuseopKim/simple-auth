package com.simpleauth.error.exception;

import com.simpleauth.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordException extends RuntimeException {

    private final ErrorCode errorCode;

    public PasswordException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
