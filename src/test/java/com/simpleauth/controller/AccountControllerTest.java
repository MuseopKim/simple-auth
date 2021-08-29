package com.simpleauth.controller;

import com.simpleauth.dto.request.*;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.error.exception.AccountNotFoundException;
import com.simpleauth.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("OK - 로그인한 유효한 사용자가 Password 변경을 요청하면 200 응답을 반환한다.")
    @Test
    void ok_updatePasswordTest() {
        // given
        Account testAccount = Account.builder()
                                .id("UserID")
                                .password("password")
                                .build();
        Account account = accountRepository.save(testAccount);

        String updatePassword = "UpdatePassword";
        UpdatePasswordRequest updateRequest = UpdatePasswordRequestBuilder.newBuilder()
                                                                .id(account.getId())
                                                                .password(updatePassword)
                                                                .confirmPassword(updatePassword)
                                                                .build();

        // when
        List<String> cookies = createLoginSessionCookie(account);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(HttpHeaders.COOKIE, cookies);

        HttpEntity updateRequestEntity = new HttpEntity(updateRequest, requestHeaders);
        ResponseEntity<AccountSummaryResponse> updateResponse = testRestTemplate.exchange("/api/accounts", HttpMethod.PUT, updateRequestEntity, AccountSummaryResponse.class);
        AccountSummaryResponse response = updateResponse.getBody();

        Account updatedAccount = accountRepository.findById(response.getId()).orElseThrow(AccountNotFoundException::new);

        // then
        assertThat(updatedAccount.getPassword()).isEqualTo(updatePassword);
    }

    private List<String> createLoginSessionCookie(Account account) {
        LoginRequest loginRequest = LoginRequestBuilder.newBuilder()
                                                .id(account.getId())
                                                .password(account.getPassword())
                                                .build();
        ResponseEntity<AccountSummaryResponse> loginResponse = testRestTemplate.postForEntity("/api/login", loginRequest, AccountSummaryResponse.class);

        return loginResponse.getHeaders().get(HttpHeaders.SET_COOKIE);
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
