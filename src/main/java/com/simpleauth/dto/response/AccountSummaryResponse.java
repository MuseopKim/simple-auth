package com.simpleauth.dto.response;

import com.simpleauth.entity.Account;
import lombok.Getter;

@Getter
public class AccountSummaryResponse {

    private final String id;

    private AccountSummaryResponse(String id) {
        this.id = id;
    }

    public static AccountSummaryResponse from(Account account) {
        return new AccountSummaryResponse(account.getId());
    }
}
