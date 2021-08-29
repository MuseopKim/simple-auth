package com.simpleauth.dto.response;

import com.simpleauth.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountIdResponse {

    private String id;

    private AccountIdResponse(String id) {
        this.id = id;
    }

    public static AccountIdResponse from(Account account) {
        return new AccountIdResponse(account.getId());
    }
}
