package com.app.controller.course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.app.controller.Context;
import com.app.controller.course.CreateCourseController.Fields;
import com.app.entity.validation.DateFormatValidation;
import com.app.entity.validation.LengthValidation;
import com.app.entity.validation.LessThanValidation;
import com.app.entity.validation.NoValidation;
import com.app.entity.validation.NullValidation;
import com.app.entity.validation.NumericValidation;
import com.app.entity.validation.PositivityValidation;
import com.app.entity.validation.Validation;

public class ControllerCourseUtil {

    public Map<String, String[]> getParameterMap(ResultSet params, Iterator<String> fields) throws SQLException {
        Map<String, String[]> result = new LinkedHashMap<>();
        while (fields.hasNext()) {
            String key = fields.next().toLowerCase();
            result.put(key, new String[] {params.getString(key)});
        }
        return result;
    }

    public Map<String, String[]> getParameterMap(Map<String, String[]> params, Iterator<String> fields) {
        Map<String, String[]> result = new LinkedHashMap<>();
        while (fields.hasNext()) {
            String key = fields.next().toLowerCase();
            populate(result, params, key);
        }
        return result;
    }

    private void populate(Map<String, String[]> map, Map<String, String[]> params, String key) {
        if (params.isEmpty()) {
            map.put(key, new String[] {key});
        } else {
            map.put(key, params.get(key));
        }
    }

    public String getIdFromUri(Context context) {
        String uri = context.request().getRequestURI().toString();
        int index = uri.lastIndexOf("/", uri.length()-1)+1;
        return (uri.endsWith("/"))
            ? uri.substring(index, uri.length()-1)
            : uri.substring(index, uri.length());
    }

    public String getSingleParameter(Map<String, String[]> map, Fields key) {
        return map.get(key.toString().toLowerCase())[0];
    }

    public Iterator<Entry<String, Validation[]>> getFieldValidation(Map<String, String[]> parameters) {
        Map<String, Validation[]> result = new LinkedHashMap<String, Validation[]>() {
            {
                put(Fields.NAME.toString().toLowerCase(), new Validation[] {
                    new NullValidation(getSingleParameter(parameters, Fields.NAME)),
                    new LengthValidation(getSingleParameter(parameters, Fields.NAME), 15)
                    });
                put(Fields.DESCRIPTION.toString().toLowerCase(), new Validation[] {
                    new NoValidation()
                    });
                put(Fields.SEATS.toString().toLowerCase(), new Validation[] {
                    new NullValidation(getSingleParameter(parameters, Fields.SEATS)),
                    new NumericValidation(getSingleParameter(parameters, Fields.SEATS)),
                    new PositivityValidation(getSingleParameter(parameters, Fields.SEATS)),
                    new LessThanValidation(getSingleParameter(parameters, Fields.SEATS), 100),
                    new LengthValidation(getSingleParameter(parameters, Fields.SEATS), 3)
                    });
                put(Fields.LOCATION.toString().toLowerCase(), new Validation[] {
                    new NullValidation(getSingleParameter(parameters, Fields.LOCATION)),
                    new LengthValidation(getSingleParameter(parameters, Fields.LOCATION), 20)
                    });
                put(Fields.START.toString().toLowerCase(), new Validation[] {
                    new NullValidation(getSingleParameter(parameters, Fields.START)),
                    new DateFormatValidation(getSingleParameter(parameters, Fields.START))
                    });
            }
        };
        return result.entrySet().iterator();
    }

}
