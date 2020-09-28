package com.app.entity.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.entity.seminar.Seminar;

public interface DbInterface {

    public ResultSet select(String select, String where) throws SQLException;

    public void insert(Seminar seminar) throws SQLException;

    public void update(String set, String where) throws SQLException;

    public void delete(String where) throws SQLException;

}
