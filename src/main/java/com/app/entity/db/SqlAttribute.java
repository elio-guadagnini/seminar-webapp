package com.app.entity.db;

public class SqlAttribute {

    private final String _key;
    private final String _value;

    public SqlAttribute(String key, String value) {
        _key = key;
        _value = value;
    }

    public String getKey() {
        return _key;
    }

    public String getValue() {
        return _value;
    }

}
