package com.simpleauth.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdatePasswordRequestTest {

    @DisplayName("Password와 ConfirmPassword가 일치하지 않을 경우 True를 반환한다.")
    @Test
    void isInvalidConfirmPasswordTest() {
        // given
        UpdatePasswordRequest request = UpdatePasswordRequestBuilder.newBuilder()
                                                                .password("Password")
                                                                .confirmPassword("DifferentPassword")
                                                                .build();

        // when
        boolean invalidConfirmPassword = request.isInvalidConfirmPassword();

        // then
        assertThat(invalidConfirmPassword).isTrue();
    }
}
