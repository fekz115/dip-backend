package org.fekz115.dip.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginOrEmailAlreadyUsed extends ServiceException {

    private final LoginOrEmailAlreadyUsedFields exceptionFields;

    @Getter
    @AllArgsConstructor
    public static class LoginOrEmailAlreadyUsedFields extends ExceptionFields {
        private final boolean loginAlreadyUsed;
        private final boolean emailAlreadyUsed;
    }
}
