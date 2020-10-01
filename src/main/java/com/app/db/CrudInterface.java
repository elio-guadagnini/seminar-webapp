package com.app.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface CrudInterface {

    public ResultSet select(Statement statement, String sql) throws SQLException;

    public void insert(Statement statement, String sql) throws SQLException;

    public void delete(Statement statement, String sql) throws SQLException;

    public void update(Statement statement, String sql) throws SQLException;

}
