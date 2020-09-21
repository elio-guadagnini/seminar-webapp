package com.app.entity.validation;

public class NumericValidation implements Validation {

    private final String _parameter;

    public NumericValidation(String parameter) {
        _parameter = parameter;
    }

    @Override
    public String getErrorMessage() {
        return "Must be integer!";
    }

    @Override
    public boolean check() {
        try {
            Integer.parseInt(_parameter);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
