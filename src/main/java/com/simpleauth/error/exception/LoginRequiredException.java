package com.simpleauth.error.exception;

import com.simpleauth.error.response.ErrorCode;

public class LoginRequiredException extends AuthException {

    public LoginRequiredException() {
        super(ErrorCode.LOGIN_REQUIRED_EXCEPTION);
    }
}
