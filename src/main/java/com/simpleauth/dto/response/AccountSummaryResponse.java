package com.simpleauth.dto.response;

import com.simpleauth.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountSummaryResponse {

    private String id;

    private AccountSummaryResponse(String id) {
        this.id = id;
    }

    public static AccountSummaryResponse from(Account account) {
        return new AccountSummaryResponse(account.getId());
    }
}
