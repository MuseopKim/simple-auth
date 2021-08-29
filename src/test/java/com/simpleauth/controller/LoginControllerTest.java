package com.simpleauth.controller;

import com.simpleauth.dto.request.LoginRequest;
import com.simpleauth.dto.request.LoginRequestBuilder;
import com.simpleauth.dto.response.AccountIdResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("OK - 일치하는 ID, Password 값으로 로그인 요청에 성공하면 세션을 생성한다.")
    @Test
    void loginSuccessTest() {
        // given
        Account testAccount = Account.builder()
                                .id("UserID")
                                .password("UserPassword")
                                .build();
        Account account = accountRepository.save(testAccount);
        LoginRequest loginRequest = LoginRequestBuilder.newBuilder()
                                                .id(account.getId())
                                                .password(account.getPassword())
                                                .build();

        // when
        ResponseEntity<AccountIdResponse> responseEntity = testRestTemplate.postForEntity("/api/login", loginRequest,
                                                                                                AccountIdResponse.class);
        String sessionCookie = responseEntity.getHeaders().get("Set-Cookie").get(0);


        // then
        assertThat(sessionCookie).contains("JSESSIONID");
    }
}
