package com.simpleauth.service;

import com.simpleauth.dto.login.LoginAccount;
import com.simpleauth.dto.request.LoginRequest;
import com.simpleauth.dto.response.AccountIdResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.error.exception.AccountNotFoundException;
import com.simpleauth.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final AccountRepository accountRepository;

    public AccountIdResponse loginBy(HttpServletRequest httpRequest, LoginRequest loginRequest) {
        Account account = accountRepository.findBy(loginRequest.getId(), loginRequest.getPassword()).orElseThrow(AccountNotFoundException::new);

        HttpSession session = httpRequest.getSession(true);
        session.setMaxInactiveInterval(60 * 30);
        session.setAttribute("loginAccount", LoginAccount.from(account));

        return AccountIdResponse.from(account);
    }
}
