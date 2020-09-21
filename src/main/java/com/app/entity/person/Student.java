package com.app.entity.person;

public class Student extends Person {

	public Student(String name, String surname) {
		super(name, surname);
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((super.getName() == null) ? 0 : super.getName().hashCode());
	    result = prime * result + ((super.getSurname() == null) ? 0 : super.getSurname().hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
		return hashCode() == obj.hashCode();
	}

}
