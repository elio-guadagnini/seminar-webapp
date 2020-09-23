package com.app.entity.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.app.controller.Context;

public interface CrudInterface {

    public ResultSet select(Context context, String sql) throws SQLException;

    public void insert(Context context, String sql, List<String> values) throws SQLException;

    public void delete(Context context, String sql) throws SQLException;

    public void update(Context context, String sql) throws SQLException;

}
