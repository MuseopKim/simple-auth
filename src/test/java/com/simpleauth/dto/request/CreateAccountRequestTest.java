package com.simpleauth.dto.request;

import com.simpleauth.error.ErrorCode;
import com.simpleauth.error.exception.InvalidConfirmPasswordException;
import com.simpleauth.error.exception.PasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateAccountRequestTest {

    @DisplayName("Account 생성 요청시 비밀번호와 확인 비밀번호가 일치하지 않으면 예외를 반환한다")
    @Test
    void isInvalidPasswordTest() {
        // given
        ErrorCode errorCode = ErrorCode.INVALID_CONFIRM_PASSWORD_ERROR;
        CreateAccountRequest request = CreateAccountRequestBuilder.newBuilder()
                                                            .id("UserID")
                                                            .password("password")
                                                            .confirmPassword("notSamePassword")
                                                            .build();

        // then
        assertThatThrownBy(() -> request.toEntity())
                .isExactlyInstanceOf(InvalidConfirmPasswordException.class)
                .hasMessage(errorCode.getMessage());
    }
}
