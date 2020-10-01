package com.app.db.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbInterface {

    public ResultSet select(String select) throws SQLException;

    public ResultSet select(String select, String where) throws SQLException;

    public void insert(String columns, String values) throws SQLException;

    public void update(String set, String where) throws SQLException;

    public void delete(String where) throws SQLException;

}
