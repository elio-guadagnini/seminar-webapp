package com.app.controller;

import com.app.entity.seminar.Seminars;

public interface Controller {

	boolean handles(String route);
	void execute(Context context, Seminars seminars) throws Exception;

}
