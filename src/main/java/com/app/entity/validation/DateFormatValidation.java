package com.app.entity.validation;

public class DateFormatValidation implements Validation {

    private final String _parameter;

    public DateFormatValidation(String parameter) {
        _parameter = parameter;
    }

    @Override
    public boolean check() {
        return (dateCondition())
            ? false
            : true;
    }

    private boolean dateCondition() {
        if (_parameter.contains("/") || _parameter.contains("-")) {
            _parameter.replace("/", ".");
            _parameter.replace("-", ".");
        }
        return ((_parameter.substring(0, 2).length() == 2)
                && (_parameter.substring(2, 4).length() == 2)
                && (_parameter.substring(4, _parameter.length()).length() == 4))
            ? true
            : false;
    }

    @Override
    public String getErrorMessage() {
        return "Must have standard format: (dd.MM.yyyy)!";
    }

}
