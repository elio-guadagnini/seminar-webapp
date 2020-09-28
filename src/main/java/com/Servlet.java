package com;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.app.controller.Context;
import com.app.controller.Controller;

public class Servlet extends HttpServlet {


//	private SQLiteConnectionPoolDataSource _ds;
//	_ds = new SQLiteConnectionPoolDataSource();
//	_ds.setUrl("jdbc:sqlite:/path");

//	private HikariDataSource _dsHikari;
//	_dsHikari = new HikariDataSource();
//  _dsHikari.setJdbcUrl("jdbc:sqlite:/course");


	private DataSource _ds;
	private Connection connection;

	@Override
	public void init() {
		try {
		    _ds = (DataSource)new InitialContext().lookup("java:/comp/env/jdbc/ds");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) {
	    try {
    		for(Controller c : new ControllerFactory().create()){
    		    if(c.handles(handleURIs(req.getRequestURI()))){
    					connection = _ds.getConnection();
    					connection.setAutoCommit(false);
    					c.execute(new Context(req, resp, connection));
    					connection.commit();
    					return ;
    			}
    		}
	    } catch (Exception e) {
	        e.printStackTrace();
	        throwInternalServerError(req, resp, e);
	    } finally {
	        try {
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	private void throwInternalServerError(HttpServletRequest req, HttpServletResponse resp, Exception error) {
        try {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            new ControllerFactory().internalError(error).execute(new Context(req, resp, null));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
	}

	private String handleURIs(String requestedUri) {
	    return (requestedUri.endsWith("/"))
	        ? requestedUri.substring(0, requestedUri.length()-1)
	        : requestedUri;
    }
}
