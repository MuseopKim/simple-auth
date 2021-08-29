package com.simpleauth.controller;

import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.request.CreateAccountRequestBuilder;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.repository.AccountRepository;
import com.simpleauth.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("CREATED - Account 생성 성공시 201 응답을 반환한다")
    @Test
    void createNewAccountTest() {
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

    @DisplayName("BAD_REQUEST - Account 생성 시 유효하지 않은 ID, PASSWORD로 요청하면 400에러를 반환한다")
    @MethodSource("invalidCreateAccountParams")
    @ParameterizedTest
    void badRequest_createNewAccountTest(String id, String password, String confirmPassword) {
        // given
        CreateAccountRequest request = CreateAccountRequestBuilder.newBuilder()
                                                            .id(id)
                                                            .password(password)
                                                            .confirmPassword(confirmPassword)
                                                            .build();

        // when
        ResponseEntity<AccountSummaryResponse> responseEntity = testRestTemplate.postForEntity("/api/accounts", request, AccountSummaryResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private static Stream<Arguments> invalidCreateAccountParams() {
        String normalId = "NormalID";
        String normalPassword = "NormalPassword";
        String blankId = "Has Blank";
        String shorterThanMinimumLengthId = "ID";
        String longerThanMaximumLengthId = "SomethingLongerThanMaximumLengthOfID";
        String shorterThanMinimumLengthPassword = "Pass";
        String longerThanMaximumLengthPassword = "SomethingLongerThanMaximumLengthOfPassword";

        return Stream.of(
                Arguments.of(shorterThanMinimumLengthId, normalPassword, normalPassword),
                Arguments.of(longerThanMaximumLengthId, normalPassword, normalPassword),
                Arguments.of(blankId, normalPassword, normalPassword),
                Arguments.of(normalId, shorterThanMinimumLengthPassword, shorterThanMinimumLengthPassword),
                Arguments.of(longerThanMaximumLengthId, longerThanMaximumLengthPassword, longerThanMaximumLengthPassword)
        );
    }
}
