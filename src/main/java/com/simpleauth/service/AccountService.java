package com.simpleauth.service;

import com.simpleauth.dto.login.LoginAccount;
import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.request.UpdatePasswordRequest;
import com.simpleauth.dto.response.AccountIdResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.error.exception.*;
import com.simpleauth.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountIdResponse createBy(CreateAccountRequest request) {
        Optional<Account> accountOptional = accountRepository.findById(request.getId());

        if (accountOptional.isPresent()) {
            throw new AccountDuplicationException();
        }

        Account account = request.toEntity();
        Account newAccount = accountRepository.save(account);
        return AccountIdResponse.from(newAccount);
    }

    @Transactional
    public AccountIdResponse updateBy(HttpServletRequest httpRequest, UpdatePasswordRequest updateRequest) {
        HttpSession session = httpRequest.getSession(false);

        if (session == null) {
            throw new LoginRequiredException();
        }

        LoginAccount loginAccount = (LoginAccount) session.getAttribute("loginAccount");

        if (loginAccount.isDifferentAccount(updateRequest.getId())) {
            throw new AccessDeniedException();
        }

        if (updateRequest.isInvalidConfirmPassword()) {
            throw new InvalidConfirmPasswordException();
        }

        Account account = accountRepository.findById(loginAccount.getId()).orElseThrow(AccountNotFoundException::new);
        account.updatePassword(updateRequest.getPassword());

        return AccountIdResponse.from(account);
    }

    @Transactional
    public void deleteBy(HttpServletRequest httpRequest, String accountId) {
        HttpSession session = httpRequest.getSession(false);

        if (session == null) {
            throw new LoginRequiredException();
        }

        LoginAccount loginAccount = (LoginAccount) session.getAttribute("loginAccount");

        if (loginAccount.isDifferentAccount(accountId)) {
            throw new AccessDeniedException();
        }

        accountRepository.deleteById(loginAccount.getId());
    }
}
