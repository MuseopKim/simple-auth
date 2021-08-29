package com.simpleauth.service;

import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.request.CreateAccountRequestBuilder;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void createByTest() {
        // given
        CreateAccountRequest request = CreateAccountRequestBuilder
                .newBuilder()
                .id("UserID")
                .password("password")
                .confirmPassword("password")
                .build();

        Account newAccount = request.toEntity();

        // when
        given(accountRepository.save(refEq(request.toEntity()))).willReturn(newAccount);

        AccountSummaryResponse response = accountService.createBy(request);

        // then
        assertThat(response.getId()).isEqualTo(newAccount.getId());
    }
}
