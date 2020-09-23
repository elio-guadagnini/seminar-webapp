package com.app.entity.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.app.controller.Context;
import com.app.entity.seminar.Seminar;

public class SeminarDbUtil {

    private final Context _context;
    private static final String REGEX = ",";
    private static final String FURTHER_REGEX = "\\s*->\\s*";

    public SeminarDbUtil(Context context) {
        _context = context;
    }

    public String selectSql(String select) {
        return "SELECT "+select+" FROM Seminar;";
    }

    public String insertSql() {
        return "INSERT INTO Seminar (name, description, location, totalSeats, start) VALUES (?, ?, ?, ?, ?);";
    }

    public String updateSql(String set, String where) {
        return "UPDATE Seminar SET "+set+" WHERE "+where+";";
    }

    public String deleteSql(String where) {
        return "DELETE FROM Seminar WHERE "+where+";";
    }

    public ResultSet queryToDb(String select) throws SQLException {
        return new CrudOperations().select(_context, selectSql(select));
    }

    public void saveToDb(Seminar seminar) throws SQLException {
        List<String> values = Arrays.asList(seminar.getName(),
                                            seminar.getDescription(),
                                            seminar.getLocation(),
                                            String.valueOf(seminar.getSeatsLeft()),
                                            seminar.getCourse().getDate());
        new CrudOperations().insert(_context, insertSql(), values);
    }

    public void editToDb(String set, String where) throws SQLException  {
        String sets = getParamsAsProperString(set);
        String wheres = getParamsAsProperString(where);
        new CrudOperations().update(_context, updateSql(sets, wheres));
    }


    public void removeFromDb(String where) throws SQLException {
        String wheres = getParamsAsProperString(where);
        new CrudOperations().delete(_context, deleteSql(wheres));
    }

    private String getParamsAsProperString(String params) throws SQLException {
        return (checkInputs(params))
            ? render(params.split(REGEX))
            : null;
    }

    private String render(String[] split) {
        String result = "";
        for (String element : split) {
            String[] singleSet = element.split(FURTHER_REGEX);
            result += valueAt(singleSet, 0) + "=" + "'" + valueAt(singleSet, 1) + "'" + " ";
        }
        return result.trim();
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
