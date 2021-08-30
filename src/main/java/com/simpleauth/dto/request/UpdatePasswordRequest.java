package com.simpleauth.dto.request;

import com.simpleauth.constants.Validation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UpdatePasswordRequest {

    private String id;

    @Size(min = Validation.PASSWORD_SIZE_MIN, max = Validation.PASSWORD_SIZE_MAX, message = Validation.PASSWORD_SIZE_MESSAGE)
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
