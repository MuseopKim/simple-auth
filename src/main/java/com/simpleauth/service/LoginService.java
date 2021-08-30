package com.simpleauth.service;

import com.simpleauth.dto.request.LoginRequest;
import com.simpleauth.dto.response.AccountIdResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.error.exception.AccountNotFoundException;
import com.simpleauth.repository.AccountRepository;
import com.simpleauth.session.HttpSessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final AccountRepository accountRepository;
    private final HttpSessionManager httpSessionManager;

    public AccountIdResponse loginBy(HttpServletRequest httpRequest, LoginRequest loginRequest) {
        Account account = accountRepository.findBy(loginRequest.getId(), loginRequest.getPassword()).orElseThrow(AccountNotFoundException::new);
        httpSessionManager.login(httpRequest, account);

        return AccountIdResponse.from(account);
    }
}
