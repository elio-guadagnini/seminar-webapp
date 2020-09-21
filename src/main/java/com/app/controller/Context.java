package com.app.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Context {

	private final HttpServletRequest _request;
	private final HttpServletResponse _response;
	private final Connection _connection;

	public Context(HttpServletRequest request, HttpServletResponse response, Connection connection) {
		_request = request;
		_response = response;
		_connection = connection;
	}

	public HttpServletRequest request() {
		return _request;
	}

	public HttpServletResponse response() {
		return _response;
	}

	public Connection connection() {
		return _connection;
	}
}
