package com.app.entity.validation;

public class GreaterThanValidation implements Validation {

    private final String _parameter;
    private final String _threshold;

    public GreaterThanValidation(String parameter, String threshold) {
        _parameter = parameter;
        _threshold = threshold;
    }

    @Override
    public boolean check() throws NumberFormatException {
        return (Integer.parseInt(_parameter) > Integer.parseInt(_threshold))
            ? false
            : true;
    }

    @Override
    public String getErrorMessage() {
        return "Must be greater than " + _threshold + "!";
    }
}
