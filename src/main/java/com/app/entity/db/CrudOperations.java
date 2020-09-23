package com.app.entity.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.app.controller.Context;

public class CrudOperations implements CrudInterface {

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


}
