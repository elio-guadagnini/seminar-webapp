package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.entity.seminar.Seminars;

public class Servlet extends HttpServlet {


//	private SQLiteConnectionPoolDataSource _ds;
//	_ds = new SQLiteConnectionPoolDataSource();
//	_ds.setUrl("jdbc:sqlite:/path");

//	private HikariDataSource _ds;
//  _ds = new HikariDataSource();
//	_ds.setJdbcUrl("jdbc:sqlite:/path");

	private DataSource _ds;
	private final Seminars _seminars = new Seminars();
	private Connection connection;

	@Override
	public void init() throws ServletException {
		try {
			_ds = (DataSource)new InitialContext().lookup("java:/comp/env/jdbc/ds");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		for(Controller c : new ControllerFactory().create()){
			if(c.handles(req.getRequestURI())){
				try {
					connection = _ds.getConnection();
					connection.setAutoCommit(false);
					c.execute(new Context(req, resp, connection), _seminars);
					connection.commit();
					return;
				} catch (Exception e) {
				    throwInternalServerError(req, resp, e);
				} finally {
				    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
			}
		}
	}

	private void throwInternalServerError(HttpServletRequest req, HttpServletResponse resp, Exception error) {
        try {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            new ControllerFactory().internalError(error).execute(new Context(req, resp, null), null);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
	}
}
