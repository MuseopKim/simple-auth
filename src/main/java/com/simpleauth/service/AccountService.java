package com.simpleauth.service;

import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountSummaryResponse createBy(CreateAccountRequest request) {
        Account account = request.toEntity();
        Account newAccount = accountRepository.save(account);
        return AccountSummaryResponse.from(newAccount);
    }
}
