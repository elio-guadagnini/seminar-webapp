package com.app.entity.person;

public class Person {

	private String _name, _surname;

	public Person(String name, String surname) {
		_name = name;
		_surname = surname;
	}
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getSurname() {
		return _surname;
	}

	public void setSurname(String surname) {
		this._surname = surname;
	}
	
	public String getFullName() {
		return _name + " " + _surname;
	}
}
