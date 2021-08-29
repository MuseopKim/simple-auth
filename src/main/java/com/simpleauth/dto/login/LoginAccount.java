package com.simpleauth.dto.login;

import com.simpleauth.entity.Account;
import lombok.Getter;

@Getter
public class LoginAccount {

    private final String id;

    private LoginAccount(String id) {
        this.id = id;
    }

    public static LoginAccount from(Account account) {
        return new LoginAccount(account.getId());
    }
}
