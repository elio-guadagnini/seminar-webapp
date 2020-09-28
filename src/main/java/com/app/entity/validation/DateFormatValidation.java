package com.app.entity.validation;

public class DateFormatValidation implements Validation {

    private final String _parameter;

    public DateFormatValidation(String parameter) {
        _parameter = parameter;
    }

    @Override
    public boolean check() {
        return (formatCondition() && characterCondition())
            ? true
            : false;
    }

    private boolean formatCondition() {
        return ((_parameter.substring(0, 2).length() == 2)
                && (_parameter.substring(3, 5).length() == 2)
                && (_parameter.substring(6, _parameter.length()).length() == 4));
    }

    private boolean characterCondition() {
        return !_parameter.matches(".*[a-zA-Z]+.*");
    }

    @Override
    public String getErrorMessage() {
        return "Must have standard format: (dd.MM.yyyy)!";
    }

}
