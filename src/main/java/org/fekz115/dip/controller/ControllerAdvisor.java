package org.fekz115.dip.controller;

import org.fekz115.dip.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginOrEmailAlreadyUsed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LoginOrEmailAlreadyUsed.LoginOrEmailAlreadyUsedFields handleLoginOrEmailAlreadyUsed(LoginOrEmailAlreadyUsed ex) {
        return ex.getExceptionFields();
    }

    @ExceptionHandler(LoginOrPasswordIncorrect.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LoginOrPasswordIncorrect.LoginOrPasswordIncorrectFields handleLoginOrPasswordIncorrect(LoginOrPasswordIncorrect ex) {
        return ex.getExceptionFields();
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserNotFound.UserNotFoundFields handleUserNotFound(UserNotFound ex) {
        return ex.getExceptionFields();
    }

    @ExceptionHandler(ActivationCodeIncorrect.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ActivationCodeIncorrect.ActivationCodeIncorrectFields handleActivationCodeIncorrect(ActivationCodeIncorrect ex) {
        return ex.getExceptionFields();
    }
}