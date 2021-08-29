package com.simpleauth.dto.login;

import com.simpleauth.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoginAccountTest {

    @DisplayName("요청 데이터의 id가 로그인 되어 있는 Account의 id와 일치하지 않을 경우 False를 반환한다.")
    @Test
    void isDifferentAccountTest() {
        // given
        Account account = Account.builder()
                            .id("UserID")
                            .password("Password")
                            .build();

        LoginAccount loginAccount = LoginAccount.from(account);

        // when
        boolean differentId = loginAccount.isDifferentAccount("DifferentID");

        // then
        assertThat(differentId).isTrue();
    }
}
