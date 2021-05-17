package org.fekz115.dip.service.request;

import lombok.Data;

public class UserServiceRequests {

    @Data
    public static class RegisterUserRequest {
        private final String login;
        private final String password;
        private final String email;
    }

    @Data
    public static class LoginUserRequest {
        private final String login;
        private final String password;
    }

    @Data
    public static class ActivateUserRequest {
        private final String login;
        private final String activationCode;
    }

}
