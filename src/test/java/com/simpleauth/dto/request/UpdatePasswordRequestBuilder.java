package com.simpleauth.dto.request;

public class UpdatePasswordRequestBuilder {

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String password;
        private String confirmPassword;

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder confirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public UpdatePasswordRequest build() {
            return new UpdatePasswordRequest(password, confirmPassword);
        }
    }
}
