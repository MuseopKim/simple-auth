package com.simpleauth.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Input
    INVALID_INPUT_ERROR(400, "요청한 ID 또는 비밀번호 값이 유효하지 않습니다. 다시 요청 해주세요."),

    // Password
    INVALID_CONFIRM_PASSWORD_ERROR(400, "비밀번호와 확인 비밀번호가 일치하지 않습니다."),

    // Entity
    ACCOUNT_NOT_FOUND_ERROR(404, "아이디와 비밀번호가 일치하는 계정을 찾을 수 없습니다. 다시 확인 후 요청 해주세요.")
    ;

    private int statusCode;
    private String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
