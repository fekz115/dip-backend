package org.fekz115.dip.service.exception;

public abstract class ExceptionFields {
    public String getErrorType() {
        String name = this.getClass().getSimpleName();
        return name.substring(0, name.length() - "Fields".length());
    }
}
