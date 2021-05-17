package org.fekz115.dip.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginOrPasswordIncorrect extends ServiceException {

    private final LoginOrPasswordIncorrectFields exceptionFields;

    @Getter
    @AllArgsConstructor
    public static class LoginOrPasswordIncorrectFields extends ExceptionFields {
        private final boolean loginCorrect;
    }
}

