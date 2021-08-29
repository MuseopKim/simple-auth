package com.simpleauth.error.exception;

import com.simpleauth.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class AccessDeniedException extends AuthException {

    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED_EXCEPTION);
    }
}
