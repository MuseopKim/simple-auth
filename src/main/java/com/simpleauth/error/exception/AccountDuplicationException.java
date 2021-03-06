package com.simpleauth.error.exception;

import com.simpleauth.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class AccountDuplicationException extends AuthException {

    public AccountDuplicationException() {
        super(ErrorCode.ACCOUNT_DUPLICATION_ERROR);
    }
}
