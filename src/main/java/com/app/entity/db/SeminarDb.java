package com.app.entity.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.controller.Context;
import com.app.entity.seminar.Seminar;

public class SeminarDb implements DbInterface {

    private final Context _context;
    private final CrudOperations _db = new CrudOperations();

    public SeminarDb(Context context) {
        _context = context;
    }

    private String selectSql(String select, String where) {
        return "SELECT "+select+" FROM Seminar WHERE "+where+";";
    }

    private String insertSql() {
        return "INSERT INTO Seminar (name, description, location, seats, start) VALUES (?, ?, ?, ?, ?);";
    }

    private String updateSql(String set, String where) {
        return "UPDATE Seminar SET "+set+" WHERE "+where+";";
    }

    private String deleteSql(String where) {
        return "DELETE FROM Seminar WHERE "+where+";";
    }

    @Override
    public ResultSet select(String select, String where) throws SQLException {
        String wheres = _db.getParamsAsProperString(where);
        return new CrudOperations().select(_context, selectSql(select, wheres));
    }

    @Override
    public void insert(Seminar seminar) throws SQLException {
        _db.insert(_context, insertSql(), seminar.getParams());
    }

    @Override
    public void update(String set, String where) throws SQLException  {
        String sets = _db.getParamsAsProperString(set);
        String wheres = _db.getParamsAsProperString(where);
        _db.update(_context, updateSql(sets, wheres));
    }

    @Override
    public void delete(String where) throws SQLException {
        String wheres = _db.getParamsAsProperString(where);
        _db.delete(_context, deleteSql(wheres));
    }

    public void getFormattingString(String params) {
        _db.getParamsAsProperString(params);
    }

}
