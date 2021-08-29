package com.simpleauth.dto.request;

public class LoginRequestBuilder {

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String password;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public LoginRequest build() {
            return new LoginRequest(id, password);
        }
    }
}
