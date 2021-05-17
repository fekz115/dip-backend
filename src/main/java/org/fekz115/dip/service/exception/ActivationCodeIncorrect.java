package org.fekz115.dip.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActivationCodeIncorrect extends ServiceException {

    private final ActivationCodeIncorrectFields exceptionFields;

    @Getter
    @AllArgsConstructor
    public static class ActivationCodeIncorrectFields extends ExceptionFields {
        private final String activationCode;
    }
}
