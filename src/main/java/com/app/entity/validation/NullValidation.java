package com.app.entity.validation;

public class NullValidation implements Validation {

    private final String _parameter;

    public NullValidation(String parameter) {
        _parameter = parameter;
    }

    @Override
    public boolean check() {
        return (_parameter.equals(null) || _parameter.equals(""))
            ? false
            : true;
    }

    @Override
    public String getErrorMessage() {
        return "Must be defined!";
    }

}
