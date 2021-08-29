package com.simpleauth.service;

import com.simpleauth.dto.login.LoginAccount;
import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.request.UpdatePasswordRequest;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.error.exception.AccessDeniedException;
import com.simpleauth.error.exception.LoginRequiredException;
import com.simpleauth.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountSummaryResponse createBy(CreateAccountRequest request) {
        Account account = request.toEntity();
        Account newAccount = accountRepository.save(account);
        return AccountSummaryResponse.from(newAccount);
    }

    public AccountSummaryResponse updateBy(HttpServletRequest httpRequest, UpdatePasswordRequest updateRequest) {
        HttpSession session = httpRequest.getSession(false);

        if (session == null) {
            throw new LoginRequiredException();
        }

        LoginAccount loginAccount = (LoginAccount) session.getAttribute("loginAccount");

        if (loginAccount.isDifferentAccount(updateRequest.getId())) {
            throw new AccessDeniedException();
        }

        return null;
    }
}
