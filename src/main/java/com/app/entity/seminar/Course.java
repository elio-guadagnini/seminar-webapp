package com.app.entity.seminar;

import java.util.Date;

import com.app.converter.DateConverter;

public class Course {

	private String _name, _description;
	private final Date _startingDate;

	public Course(String name, String description, Date date) {
		_name = name;
		_description = description;
		_startingDate = date;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String _description) {
		this._description = _description;
	}

	public String getDate() {
	    return new DateConverter().convertFromDateToString(_startingDate);
	}

	@Override
	public String toString() {
	    return _name + " " + _description + " " + getDate();
	}

}
