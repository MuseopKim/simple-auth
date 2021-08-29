package com.simpleauth.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UpdatePasswordRequest {

    private String id;

    @Size(min = 5, max = 30, message = "Password 값은 6자 이상 30자 이하입니다.")
    private String password;

    private String confirmPassword;

    UpdatePasswordRequest(String id, String password, String confirmPassword) {
        this.id = id;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public boolean isInvalidConfirmPassword() {
        return !password.equals(confirmPassword);
    }
}
