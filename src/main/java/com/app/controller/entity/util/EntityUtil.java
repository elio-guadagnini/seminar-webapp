package com.app.controller.entity.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EntityUtil {

    public Map<String, String[]> getParameterMap(Map<String, String[]> params, List<String> fields) {
        Map<String, String[]> result = new LinkedHashMap<>();
        for (String field : fields) {
            populate(result, params, field);
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

    public String getIdFromUri(String uri) {
        int index = uri.lastIndexOf("/", uri.length()-2)+1;
        return (uri.endsWith("/"))
            ? uri.substring(index, uri.length()-1)
            : uri.substring(index, uri.length());
    }
}
