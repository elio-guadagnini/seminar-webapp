package com.app.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnumConverter {

    List<String> _result;

    public EnumConverter() {
        _result = new ArrayList<>();
    }

    public Iterator<String> convertEnumsToStrings(Enum<?>[] enums) {
        for (Enum<?> element : enums) {
            _result.add(element.name());
        }
        return _result.iterator();
    }

}
