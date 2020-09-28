package com.app.controller;

public interface Controller {

	boolean handles(String route);
	void execute(Context context) throws Exception;

}
