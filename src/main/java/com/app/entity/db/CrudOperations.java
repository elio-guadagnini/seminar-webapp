package com.app.entity.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.app.controller.Context;

public class CrudOperations implements CrudInterface {

    private static final String REGEX = ",";
    private static final String FURTHER_REGEX = "\\s*->\\s*";
    private PreparedStatement _pstmt;

    public CrudOperations() {
    }

    @Override
    public ResultSet select(Context context, String sql) throws SQLException {
        _pstmt = context.connection().prepareStatement(sql);
        return _pstmt.executeQuery();
    }

    @Override
    public void insert(Context context, String sql, List<String> values) throws SQLException {
        _pstmt = context.connection().prepareStatement(sql);
        for (int i = 0; i < values.size(); i++) {
            _pstmt.setString(i + 1, values.get(i));
        }
        _pstmt.executeUpdate();
    }

    @Override
    public void delete(Context context, String sql) throws SQLException {
        _pstmt = context.connection().prepareStatement(sql);
        _pstmt.executeUpdate();
    }

    @Override
    public void update(Context context, String sql) throws SQLException {
        _pstmt = context.connection().prepareStatement(sql);
        _pstmt.executeUpdate();
    }

    @Override
    public String getParamsAsProperString(String params) {
        return (checkInputs(params))
            ? render(params.split(REGEX))
            : "1=1";
    }

    private String render(String[] split) {
        String result = "";
        for (String element : split) {
            String[] singleSet = element.split(FURTHER_REGEX);
            result += valueAt(singleSet, 0) + "=" + "'" + valueAt(singleSet, 1) + "'" + ", ";
        }
        return result.trim().substring(0, result.trim().length()-1);
    }

    private String valueAt(String[] values, int index){
        try{
            return values[index];
        } catch(ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    private boolean checkInputs(String...args) {
        for (String element : args) {
            if (isBlank(element)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBlank(String string) {
        return string == null || string.trim().isEmpty();
    }

}
