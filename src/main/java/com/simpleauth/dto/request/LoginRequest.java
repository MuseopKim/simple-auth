package com.simpleauth.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginRequest {

    private String id;
    private String password;

    LoginRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
