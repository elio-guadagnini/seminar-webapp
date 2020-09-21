package com.app.entity.validation;

public class NoValidation implements Validation {

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public String getErrorMessage() {
        return "";
    }

}
