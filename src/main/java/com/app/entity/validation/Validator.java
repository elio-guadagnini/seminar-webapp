package com.app.entity.validation;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.app.controller.CreateCourseController.Fields;

public class Validator {

    public boolean check(Iterator<Entry<Fields, Validation[]>> iter) {
        while (iter.hasNext()) {
            Entry<Fields, Validation[]> element = iter.next();
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

    public Map<Fields, String> feedbacks(Iterator<Entry<Fields, Validation[]>> iter) {
        Map<Fields, String> result = new LinkedHashMap<>();
        while (iter.hasNext()) {
            Entry<Fields, Validation[]> element = iter.next();
            checkValidations(result, element);
        }
        return result;
    }

    private void checkValidations(Map<Fields, String> feedbacks, Entry<Fields, Validation[]> element) {
        for (Validation val : element.getValue()) {
            try {
                if (!val.check()) {
                    insertElement(feedbacks, element, val);
                }
            } catch(Exception e) {
                insertElement(feedbacks, element, val);
            }
        }
        if (!feedbacks.containsKey(element.getKey())) {
            feedbacks.put(element.getKey(), null);
        }
    }

    private void insertElement(Map<Fields, String> feedbacks, Entry<Fields, Validation[]> element, Validation val) {
        if(feedbacks.containsKey(element.getKey())) {
            feedbacks.put(element.getKey(),
                          feedbacks.get(element.getKey()) + " " + val.getErrorMessage());
        } else {
            feedbacks.put(
                element.getKey(),
                val.getErrorMessage());
        }
    }

}
