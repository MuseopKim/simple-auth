package com.simpleauth.constants;

public class Validation {

    // ID
    public static final int ID_SIZE_MIN = 3;
    public static final int ID_SIZE_MAX = 20;
    public static final String ID_SIZE_MESSAGE = "ID 값은 3자 이상 20자 이하입니다.";

    public static final String ID_PATTERN_REGEX = "^[a-zA-Z0-9]*$";
    public static final String ID_PATTERN_MESSAGE = "ID는 공백 없이 영문 숫자만 허용합니다.";

    // PASSWORD
    public static final int PASSWORD_SIZE_MIN = 6;
    public static final int PASSWORD_SIZE_MAX = 30;
    public static final String PASSWORD_SIZE_MESSAGE = "Password 값은 6자 이상 30자 이하입니다.";
}
