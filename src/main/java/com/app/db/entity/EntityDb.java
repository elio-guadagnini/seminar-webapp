package com.app.db.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.app.db.CrudOperations;

public class EntityDb implements DbInterface {

    private final String _tableName;
    private final Statement _statement;
    private final CrudOperations _db;

    public EntityDb(String tableName, Statement statement) {
        _tableName = tableName;
        _statement = statement;
        _db = new CrudOperations();
    }

    private String selectSql(String select, String where) {
        return "SELECT "+select+" FROM "+_tableName+" WHERE "+where+";";
    }

    private String selectSql(String select) {
        return "SELECT "+select+" FROM "+_tableName+";";
    }

    private String insertSql(String columns, String values) {
        return "INSERT INTO "+_tableName+" ("+columns+") VALUES ("+values+");";
    }

    private String updateSql(String set, String where) {
        return "UPDATE "+_tableName+" SET "+set+" WHERE "+where+";";
    }

    private String deleteSql(String where) {
        return "DELETE FROM "+_tableName+" WHERE "+where+";";
    }

    @Override
    public ResultSet select(String select, String where) throws SQLException {
        String sql = selectSql(select, where);
        System.out.println(sql);
        return _db.select(_statement, sql);
    }

    @Override
    public ResultSet select(String select) throws SQLException {
        String sql = selectSql(select);
        return _db.select(_statement, sql);
    }

    @Override
    public void insert(String columns, String values) throws SQLException {
        String sql = insertSql(columns, values);
        _db.insert(_statement, sql);
    }

    @Override
    public void update(String set, String where) throws SQLException {
        String sql = updateSql(set, where);
        _db.update(_statement, sql);
    }

    @Override
    public void delete(String where) throws SQLException {
        String sql = deleteSql(where);
        _db.delete(_statement, sql);
    }

}
