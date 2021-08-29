package com.simpleauth.controller;

import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountSummaryResponse createNew(@RequestBody  CreateAccountRequest request) {
          return accountService.createBy(request);
    }
}