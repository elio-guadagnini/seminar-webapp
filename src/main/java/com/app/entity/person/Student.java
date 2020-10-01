package com.app.entity.person;

import java.util.Arrays;
import java.util.List;

public class Student extends Person {

    public final static String NAME = "name";
    public final static String SURNAME = "surname";
    public final static List<String> FIELDS = Arrays.asList(NAME, SURNAME);

	public Student(String name, String surname) {
	    super(name, surname);
	}

	public List<String> getValues() {
        return Arrays.asList(
            _name,
            _surname);
    }

}
