package com.simpleauth.controller;

import com.simpleauth.dto.request.LoginRequest;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public AccountSummaryResponse login(HttpServletRequest httpRequest, @RequestBody LoginRequest loginRequest) {
        return loginService.loginBy(httpRequest, loginRequest);
    }
}
