package com.app.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.app.controller.course.ControllerCourseUtil;
import com.app.controller.course.CreateCourseController;
import com.app.controller.course.CourseListController;
import com.app.controller.course.CreateCourseController.Fields;
import com.app.entity.validation.Validator;

public class MainControllerTest {

	@Test
	public void handlesRoute() {
		assertTrue(new CourseListController().handles("/"));
	}

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
        for (Fields element : Fields.values()) {
            parameters.put(element.toString().toLowerCase(), new String[] {strings[i]});
            i++;
        }

        Map<String, String> feedbacks = new Validator().feedbacks(
            new ControllerCourseUtil().getFieldValidation(parameters));

        assertThat(feedbacks.size(), is(4));
        assertThat(feedbacks.get(Fields.NAME.toString().toLowerCase()), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.SEATS.toString().toLowerCase()), is(equalTo("Must be defined! Must be integer! Must be positive! Must be less than a threshold (100)!")));
        assertThat(feedbacks.get(Fields.LOCATION.toString().toLowerCase()), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.START.toString().toLowerCase()), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumber() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"", "", "2", "", "11.02.2020"};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element.toString().toLowerCase(), new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
            new ControllerCourseUtil().getFieldValidation(parameters));

        assertThat(feedbacks.size(), is(2));
        assertThat(feedbacks.get(Fields.NAME.toString().toLowerCase()), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.LOCATION.toString().toLowerCase()), is(equalTo("Must be defined!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumberAndPositiveSeats() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciaoo123456789017", "", "2", "", ""};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element.toString().toLowerCase(), new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
            new ControllerCourseUtil().getFieldValidation(parameters));

        assertThat(feedbacks.size(), is(3));
        assertThat(feedbacks.get(Fields.NAME.toString().toLowerCase()), is(equalTo("Must be max 15 characters")));
        assertThat(feedbacks.get(Fields.LOCATION.toString().toLowerCase()), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.START.toString().toLowerCase()), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumberAndNegativeSeats() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciao", "", "102", "", ""};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element.toString().toLowerCase(), new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
	        new ControllerCourseUtil().getFieldValidation(parameters));

	    assertThat(feedbacks.size(), is(3));
	    assertThat(feedbacks.get(Fields.SEATS.toString().toLowerCase()), is(equalTo("Must be less than a threshold (100)!")));
	    assertThat(feedbacks.get(Fields.LOCATION.toString().toLowerCase()), is(equalTo("Must be defined!")));
	    assertThat(feedbacks.get(Fields.START.toString().toLowerCase()), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithPositiveNumberAndPositiveSeats() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciao", "", "2", "", ""};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element.toString().toLowerCase(), new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
            new ControllerCourseUtil().getFieldValidation(parameters));

        assertThat(feedbacks.size(), is(2));
        assertThat(feedbacks.get(Fields.LOCATION.toString().toLowerCase()), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.START.toString().toLowerCase()), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesCorrectCreateCourseRoute() {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"sw eng", "sw eng desc", "2", "lugano", "01.01.2000"};
	    Map<String, String[]> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element.toString().toLowerCase(), new String[] {strings[i]});
	        i++;
	    }

	    Map<String, String> feedbacks = new Validator().feedbacks(
	        new ControllerCourseUtil().getFieldValidation(parameters));

	    assertThat(feedbacks.size(), is(0));
	}

}
