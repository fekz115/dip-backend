package org.fekz115.dip.service.exception;

import lombok.Getter;

@Getter
public class UserNotFound extends ServiceException {
    private final UserNotFoundFields exceptionFields = new UserNotFoundFields();
    public static class UserNotFoundFields extends ExceptionFields {}
}