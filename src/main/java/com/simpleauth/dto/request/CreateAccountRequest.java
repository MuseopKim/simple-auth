package com.simpleauth.dto.request;

import com.simpleauth.constants.Validation;
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

    @Pattern(regexp = Validation.ID_PATTERN_REGEX, message = Validation.ID_PATTERN_MESSAGE)
    @Size(min = Validation.ID_SIZE_MIN, max = Validation.ID_SIZE_MAX, message = Validation.ID_SIZE_MESSAGE)
    private String id;

    @Size(min = Validation.PASSWORD_SIZE_MIN, max = Validation.PASSWORD_SIZE_MAX, message = Validation.PASSWORD_SIZE_MESSAGE)
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
