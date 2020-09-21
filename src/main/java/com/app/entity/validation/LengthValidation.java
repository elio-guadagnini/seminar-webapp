package com.app.entity.validation;

public class LengthValidation implements Validation {

    private final String _parameter;
    private final int _length;

    public LengthValidation(String parameter, int length) {
        _parameter = parameter;
        _length = length;
    }

    @Override
    public boolean check() {
        return (_parameter.length() > _length)
            ? false
            : true;
    }

    @Override
    public String getErrorMessage() {
        return "Must be max "+_length+" characters";
    }

}
