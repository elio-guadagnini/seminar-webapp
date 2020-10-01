package com.app.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.app.controller.entity.course.CreateCourseController;
import com.app.entity.seminar.Seminar;
import com.app.entity.validation.Validator;

public class MainControllerTest {

	@Test
	public void handlesCreateCourseRoute() {
	    assertTrue(new CreateCourseController().handles("/course/create"));
	}

	@Test
    public void handlesWrongCreateCourseRouteWithPositiveNumber() {
        new CreateCourseController().handles("/course/create");
        String[] strings = {"", "", "", "", ""};
        Map<String, String[]> parameters = new LinkedHashMap<>();
        int i=0;
        for (String element : Seminar.FIELDS) {
            parameters.put(element, new String[] {strings[i]});
            i++;
        }

        Map<String, String> feedbacks = new Validator().feedbacks(
            new CreateCourseController().getFieldValidation(parameters));

        assertThat(feedbacks.size(), is(4));
        assertThat(feedbacks.get(Seminar.NAME), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Seminar.SEATS), is(equalTo("Must be defined! Must be integer! Must be positive! Must be less than a threshold (100)!")));
        assertThat(feedbacks.get(Seminar.LOCATION), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Seminar.START), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumber() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"", "", "2", "", "11.02.2020"};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (String element : Seminar.FIELDS) {
	        parameters.put(element, new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
            new CreateCourseController().getFieldValidation(parameters));

        assertThat(feedbacks.size(), is(2));
        assertThat(feedbacks.get(Seminar.NAME), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Seminar.LOCATION), is(equalTo("Must be defined!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumberAndPositiveSeats() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciaoo123456789017", "", "2", "", ""};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (String element : Seminar.FIELDS) {
	        parameters.put(element, new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
            new CreateCourseController().getFieldValidation(parameters));

        assertThat(feedbacks.size(), is(3));
        assertThat(feedbacks.get(Seminar.NAME), is(equalTo("Must be max 15 characters")));
        assertThat(feedbacks.get(Seminar.LOCATION), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Seminar.START), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumberAndNegativeSeats() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciao", "", "102", "", ""};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (String element : Seminar.FIELDS) {
	        parameters.put(element, new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
	        new CreateCourseController().getFieldValidation(parameters));

	    assertThat(feedbacks.size(), is(3));
	    assertThat(feedbacks.get(Seminar.SEATS), is(equalTo("Must be less than a threshold (100)!")));
	    assertThat(feedbacks.get(Seminar.LOCATION), is(equalTo("Must be defined!")));
	    assertThat(feedbacks.get(Seminar.START), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithPositiveNumberAndPositiveSeats() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciao", "", "2", "", ""};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (String element : Seminar.FIELDS) {
	        parameters.put(element, new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
            new CreateCourseController().getFieldValidation(parameters));

        assertThat(feedbacks.size(), is(2));
        assertThat(feedbacks.get(Seminar.LOCATION), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Seminar.START), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesCorrectCreateCourseRoute() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"sw eng", "sw eng desc", "2", "lugano", "01.01.2000"};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (String element : Seminar.FIELDS) {
	        parameters.put(element, new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
	        new CreateCourseController().getFieldValidation(parameters));

	    assertThat(feedbacks.size(), is(0));
	}

}
