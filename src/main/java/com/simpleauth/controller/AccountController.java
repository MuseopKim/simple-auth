package com.simpleauth.controller;

import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.request.UpdatePasswordRequest;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountSummaryResponse createNew(@RequestBody @Valid CreateAccountRequest request) {
          return accountService.createBy(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public AccountSummaryResponse updatePassword(HttpServletRequest httpRequest,
                                                 @RequestBody @Valid UpdatePasswordRequest updateRequest) {
        return accountService.updateBy(httpRequest, updateRequest);
    }
}
