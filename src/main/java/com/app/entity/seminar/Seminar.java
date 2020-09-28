package com.app.entity.seminar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Seminar {

	private String _location;
	private int _availableSeats;
	private final Course _course;
	private final Collection<Enrolment> _enrolments;

	public Seminar(Course course, String location, int availableSeats) {
		_course = course;
		_location = location;
		_availableSeats = availableSeats;
		_enrolments = new ArrayList<>();
	}

	public Course getCourse() {
	    return _course;
	}

	public String getName() {
		return _course.getName();
	}

	public void setName(String name) {
		_course.setName(name);
	}

	public String getDescription() {
		return _course.getDescription();
	}

	public void setDescription(String description) {
		_course.setDescription(description);
	}

	public String getLocation() {
		return _location;
	}

	public void setLocation(String location) {
		this._location = location;
	}

	public int getSeatsLeft() {
		return _availableSeats;
	}

	public void setSeatsLeft(int availableSeats) {
		_availableSeats = availableSeats;
	}

	public void addEnrolment(Enrolment enrolment) {
		_enrolments.add(enrolment);
		_availableSeats--;
	}

	public void removeEnrolment(Enrolment enrolment) {
		_enrolments.remove(enrolment);
		_availableSeats++;
	}

	public Collection<Enrolment> getEnrolments() {
	    return _enrolments;
	}

	public List<String> getParams() {
	    return Arrays.asList(_course.getName(),
	                         _course.getDescription(),
	                         _location,
	                         String.valueOf(_availableSeats),
	                         _course.getDate());
	}

	public ArrayList<String> getStudentList() {
		ArrayList<String> students = new ArrayList<>();
		for (Enrolment enrolment : _enrolments)
			students.add(enrolment.getStudent().getFullName());

		return students;
	}

	public String studentListToString() {
	    String result = "";
		for (Enrolment enrolment : _enrolments)
			result += enrolment.getStudent().getFullName() + "\n";
		return result;
	}

	@Override
	public String toString() {
	    String result = getName() + " - " + _location + " - " + _availableSeats + "\n";
	    result += _course.toString() + "\n";
	    return result + studentListToString();
	}

}
