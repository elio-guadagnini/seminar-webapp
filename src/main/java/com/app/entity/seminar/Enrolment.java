package com.app.entity.seminar;

import com.app.entity.person.Student;

public class Enrolment {

	private final Student _student;

	public Enrolment(Student student) {
		_student = student;
	}

	public Student getStudent() {
		return _student;
	}

}
