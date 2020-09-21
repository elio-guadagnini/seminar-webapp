package com.app.entity.validation;

public class LessThanValidation implements Validation {

    private final String _parameter;
    private final int _threshold;

    public LessThanValidation(String parameter, int threshold) {
        _parameter = parameter;
        _threshold = threshold;
    }

    @Override
    public boolean check() {
        return (Integer.parseInt(_parameter) >= _threshold)
            ? false
            : true;
    }

    @Override
    public String getErrorMessage() {
        return "Must be less than a threshold ("+_threshold+")!";
    }

}
