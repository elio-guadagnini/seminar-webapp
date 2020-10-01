package com.app.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudOperations implements CrudInterface {

    @Override
    public ResultSet select(Statement statement, String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    @Override
    public void insert(Statement statement, String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Statement statement, String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Statement statement, String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

}
