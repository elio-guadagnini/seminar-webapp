package com.app.entity.validation;

public class PositivityValidation implements Validation {

    private final String _parameter;

    public PositivityValidation(String parameter) {
        _parameter = parameter;
    }

    @Override
    public boolean check() {
        return (Integer.parseInt(_parameter) <= 0)
            ? false
            : true;
    }

    @Override
    public String getErrorMessage() {
        return "Must be positive!";
    }
}
