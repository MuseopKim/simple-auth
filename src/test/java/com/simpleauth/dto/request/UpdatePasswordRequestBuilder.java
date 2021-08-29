package com.simpleauth.dto.request;

public class UpdatePasswordRequestBuilder {

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String password;
        private String confirmPassword;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder confirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public UpdatePasswordRequest build() {
            return new UpdatePasswordRequest(id, password, confirmPassword);
        }
    }
}
