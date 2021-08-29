package com.simpleauth.error.exception;

import com.simpleauth.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidConfirmPasswordException extends PasswordException {

    public InvalidConfirmPasswordException() {
        super(ErrorCode.INVALID_CONFIRM_PASSWORD_ERROR);
    }
}
