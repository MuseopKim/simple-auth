package com.simpleauth.service;

import com.simpleauth.dto.request.CreateAccountRequest;
import com.simpleauth.dto.request.CreateAccountRequestBuilder;
import com.simpleauth.dto.response.AccountSummaryResponse;
import com.simpleauth.entity.Account;
import com.simpleauth.error.response.ErrorCode;
import com.simpleauth.error.exception.AccountDuplicationException;
import com.simpleauth.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @DisplayName("Account 생성이 완료되면 생성된 ID 값을 리턴한다")
    @Test
    void createByTest() {
        // given
        CreateAccountRequest request = CreateAccountRequestBuilder.newBuilder()
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

    @DisplayName("요청한 ID가 이미 존재할 경우 에러를 반환한다.")
    @Test
    void createDuplicationTest() {
        // given
        ErrorCode errorCode = ErrorCode.ACCOUNT_DUPLICATION_ERROR;

        CreateAccountRequest request = CreateAccountRequestBuilder.newBuilder()
                                                            .id("UserID")
                                                            .password("password")
                                                            .confirmPassword("password")
                                                            .build();
        Account account = Account.builder()
                                .id("UserID")
                                .password("password")
                                .build();

        // when
        given(accountRepository.findById(eq(request.getId()))).willReturn(Optional.of(account));

        // then
        assertThatThrownBy(() -> accountService.createBy(request))
                .isExactlyInstanceOf(AccountDuplicationException.class)
                .hasMessage(errorCode.getMessage());
    }
}
