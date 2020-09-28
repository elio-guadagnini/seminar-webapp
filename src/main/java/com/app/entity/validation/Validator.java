package com.app.entity.validation;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Validator {

    public boolean check(Iterator<Entry<String, Validation[]>> iter) {
        while (iter.hasNext()) {
            Entry<String, Validation[]> element = iter.next();
            for (Validation val : element.getValue()) {
                try {
                    if (!val.check()) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

    public Map<String, String> feedbacks(Iterator<Entry<String, Validation[]>> iter) {
        Map<String, String> result = new LinkedHashMap<>();
        while (iter.hasNext()) {
            Entry<String, Validation[]> element = iter.next();
            checkValidations(result, element);
        }
        return result;
    }

    private void checkValidations(Map<String, String> feedbacks, Entry<String, Validation[]> element) {
        for (Validation val : element.getValue()) {
            try {
                if (!val.check()) {
                    insertElement(feedbacks, element, val);
                }
            } catch(Exception e) {
                insertElement(feedbacks, element, val);
            }
        }
    }

    private void insertElement(Map<String, String> feedbacks, Entry<String, Validation[]> element, Validation val) {
        if(feedbacks.containsKey(element.getKey())) {
            feedbacks.put(element.getKey(), feedbacks.get(element.getKey()) + " " + val.getErrorMessage());
        } else {
            feedbacks.put(element.getKey(), val.getErrorMessage());
        }
    }

}
