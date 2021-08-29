package com.simpleauth.controller;

import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.request.CreateAccountRequestBuilder;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.repository.AccountRepository;
import com.simpleauth.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("성공 - Account 생성 API 테스트")
    @Test
    void createNewTest() {
        // given
        CreateAccountRequest request = CreateAccountRequestBuilder.newBuilder()
                                                                .id("UserID")
                                                                .password("password")
                                                                .confirmPassword("password")
                                                                .build();

        // when
        ResponseEntity<AccountSummaryResponse> responseEntity = testRestTemplate.postForEntity("/api/accounts", request, AccountSummaryResponse.class);
        AccountSummaryResponse response = responseEntity.getBody();
        Account newAccount = accountRepository.findById(request.getId()).orElseThrow(RuntimeException::new);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getId()).isEqualTo(request.getId());
        assertThat(newAccount.getPassword()).isEqualTo(request.getPassword());
    }
}
