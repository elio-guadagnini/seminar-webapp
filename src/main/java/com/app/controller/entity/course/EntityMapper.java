package com.app.controller.entity.course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.app.controller.Context;
import com.app.db.entity.EntityDb;

public class EntityMapper {

    private static final String REGEX = "\\s*->\\s*";

    private final String _tableName;
    private final Statement _statement;
    private final List<String> _fields;

    public EntityMapper(Context context, String tableName) throws SQLException {
        this(context, tableName, null);
    }

    public EntityMapper(Context context, String tableName, List<String> fields) throws SQLException {
        _statement = context.connection().createStatement();
        _tableName = tableName;
        _fields = fields;
    }

    public List<Map<String, String>> findAll() throws SQLException {
        ResultSet rs = null;
        try {
            rs = new EntityDb(_tableName, _statement).select("*");
            return convertToListMap(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            rs.close();
            _statement.close();
        }
    }

    public Map<String, String[]> findById(String id) throws SQLException {
        ResultSet rs = null;
        try {
            String where = "id = '" + id + "'";
            rs = new EntityDb(_tableName, _statement).select("*", where);
            return convertToMap(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            rs.close();
            _statement.close();
        }
    }

    public Map<String, String[]> findByConditions(String conditions) throws SQLException {
        ResultSet rs = null;
        try {
            String where = formatConditions(conditions);
            rs = new EntityDb(_tableName, _statement).select("*", where);
            return convertToMap(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            rs.close();
            _statement.close();
        }
    }

    public void add(List<String> params) throws SQLException {
        try {
            String columns = formatFieldsForInsert();
            String values = formatParametersForInsert(params);
            new EntityDb(_tableName, _statement).insert(columns, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _statement.close();
        }
    }

    public void editById(String id, Map<String, String[]> parameters) throws SQLException {
        try {
            String where = "id = '" + id + "'";
            String set = formatParameters(parameters);
            new EntityDb(_tableName, _statement).update(set, where);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _statement.close();
        }
    }

    public void editByConditions(String conditions, Map<String, String[]> parameters) throws SQLException {
        try {
            String where = formatConditions(conditions);
            String set = formatParameters(parameters);
            new EntityDb(_tableName, _statement).update(set, where);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _statement.close();
        }
    }

    public void removeById(String id) throws SQLException {
        try {
            String where = "id = '" + id + "'";
            new EntityDb(_tableName, _statement).delete(where);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _statement.close();
        }
    }

    public void removeByConditions(String conditions) throws SQLException {
        try {
            String where = formatConditions(conditions);
            new EntityDb(_tableName, _statement).delete(where);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _statement.close();
        }
    }

    private List<Map<String, String>> convertToListMap(ResultSet rs) throws SQLException {
        List<Map<String, String>> result = new ArrayList<>();
        while (rs.next()) {
            Map<String, String> element = new LinkedHashMap<>();
            element.put("id", rs.getString("id"));
            for (int i=0;i<_fields.size();i++) {
                element.put(_fields.get(i), rs.getString(_fields.get(i)));
            }
            result.add(element);
        }
        return result;
    }

    private Map<String, String[]> convertToMap(ResultSet rs) throws SQLException {
        Map<String, String[]> result = new LinkedHashMap<>();
        for (int i=0;i<_fields.size();i++) {
            result.put(_fields.get(i), new String[] {rs.getString(_fields.get(i))});
        }
        return result;
    }

    public String formatConditions(String conditions) {
        String result = "";
        for (String condition : conditions.split(",")) {
            String[] split = condition.split(REGEX);
            result += split[0] + " = '" + split[1] + "',";
        }
        return result.trim().substring(0, result.length()-1);
    }

    public String formatParameters(Map<String, String[]> parameters) {
        String result = "";
        for (Entry<String, String[]> param : parameters.entrySet()) {
            result += param.getKey() + " = '" + param.getValue()[0] + "',";
        }
        return result.trim().substring(0, result.length()-1);
    }


    private String formatFieldsForInsert() {
        String result = "";
        for (String field : _fields) {
            result += field + ",";
        }
        return result.trim().substring(0, result.length()-1);
    }

    private String formatParametersForInsert(List<String> values) {
        String result = "";
        for (String value : values) {
            result += "'" + value + "',";
        }
        return result.trim().substring(0, result.length()-1);
    }
}
