package com.app.entity.seminar;

import java.util.Calendar;

public class Course {

	private String _name, _description;
	private int _id;
	private final Calendar _startingDate;

	public Course(String name, int id, String description, Calendar startingDate) {
		_name = name;
		_id = id;
		_description = description;
		_startingDate = startingDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String _description) {
		this._description = _description;
	}

	public String getDate() {
	    return _startingDate.get(Calendar.DAY_OF_MONTH) + "/"
                           + _startingDate.get(Calendar.MONTH) + "/"
                           + _startingDate.get(Calendar.YEAR);
	}

	public String getFullName() {
		return _name+" "+_id;
	}

	@Override
	public String toString() {
	    return getFullName() + " " + _description + " " + getDate();
	}

}
