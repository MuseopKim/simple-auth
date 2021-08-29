package com.simpleauth.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_INPUT_ERROR(400, "요청한 ID 또는 비밀번호 값이 유효하지 않습니다. 다시 요청 해주세요.");

    private int statusCode;
    private String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
