package com.simpleauth.dto.request;

import com.simpleauth.entity.Account;
import com.simpleauth.error.exception.InvalidConfirmPasswordException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateAccountRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "ID는 공백 없이 영문 숫자만 허용합니다.")
    @Size(min = 3, max = 20, message = "ID 값은 3자 이상 20자 이하입니다.")
    private String id;

    @Size(min = 6, max = 30, message = "Password 값은 6자 이상 30자 이하입니다.")
    private String password;

    private String confirmPassword;

    CreateAccountRequest(String id, String password, String confirmPassword) {
        this.id = id;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Account toEntity() {
        if (isInvalidConfirmPassword()) {
            throw new InvalidConfirmPasswordException();
        }

        return Account.builder()
                .id(id)
                .password(password)
                .build();
    }

    private boolean isInvalidConfirmPassword() {
        return !this.password.equals(this.confirmPassword);
    }
}
