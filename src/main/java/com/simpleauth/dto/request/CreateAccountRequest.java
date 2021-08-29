package com.simpleauth.dto.request;

import com.simpleauth.entity.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateAccountRequest {

    private String id;
    private String password;
    private String confirmPassword;

    CreateAccountRequest(String id, String password, String confirmPassword) {
        this.id = id;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Account toEntity() {
        if (isInvalidPassword()) {
            // TODO : 구체적인 예외로 리팩토링
            throw new RuntimeException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        return Account.builder()
                .id(id)
                .password(password)
                .build();
    }

    private boolean isInvalidPassword() {
        return !this.password.equals(this.confirmPassword);
    }
}
