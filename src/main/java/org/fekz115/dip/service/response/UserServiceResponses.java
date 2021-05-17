package org.fekz115.dip.service.response;

import lombok.Data;
import org.fekz115.dip.model.User;

public class UserServiceResponses {

    @Data
    public static class RegisterUserResponse {
        private final User user;
    }

    @Data
    public static class LoginUserResponse {
        private final User user;
    }

    @Data
    public static class ActivateUserResponse {
        private final User user;
    }
}
