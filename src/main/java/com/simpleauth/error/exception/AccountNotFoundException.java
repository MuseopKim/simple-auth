package com.simpleauth.error.exception;

import com.simpleauth.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class AccountNotFoundException extends EntityNotFoundException {

    public AccountNotFoundException() {
        super(ErrorCode.ACCOUNT_NOT_FOUND_ERROR);
    }
}
