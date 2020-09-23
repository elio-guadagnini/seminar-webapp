package com.app.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.app.controller.CreateCourseController.Fields;
import com.app.entity.validation.Validator;

public class MainControllerTest {

	@Test
	public void handlesRoute() throws Exception {
		assertTrue(new ShowCourseListController().handles("/"));
	}

	@Test
	public void handlesCreateCourseRoute() throws Exception {
	    assertTrue(new CreateCourseController().handles("/course/create/"));
	}

	@Test
    public void handlesWrongCreateCourseRouteWithPositiveNumber() throws Exception {
        new CreateCourseController().handles("/course/create");
        String[] strings = {"", "", "", "", ""};
        Map<Fields, String> parameters = new LinkedHashMap<>();
        int i=0;
        for (Fields element : Fields.values()) {
            parameters.put(element, strings[i]);
            i++;
        }

        Map<Fields, String> feedbacks = new Validator().feedbacks(
            new CreateCourseController().getFieldsValidationMap(parameters));

        assertThat(feedbacks.size(), is(5));
        assertThat(feedbacks.get(Fields.NAME), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.SEATS), is(equalTo("Must be defined! Must be integer! Must be positive! Must be less than a threshold (100)!")));
        assertThat(feedbacks.get(Fields.LOCATION), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.DATE), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumber() throws Exception {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"", "", "2", "", "11.02.2020"};
	    Map<Fields, String> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element, strings[i]);
	        i++;
	    }

	    Map<Fields, String> feedbacks = new Validator().feedbacks(
            new CreateCourseController().getFieldsValidationMap(parameters));

        assertThat(feedbacks.size(), is(5));
        assertThat(feedbacks.get(Fields.NAME), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.LOCATION), is(equalTo("Must be defined!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumberAndPositiveSeats() throws Exception {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciaoo123456789017", "", "2", "", ""};
	    Map<Fields, String> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element, strings[i]);
	        i++;
	    }

	    Map<Fields, String> feedbacks = new Validator().feedbacks(
            new CreateCourseController().getFieldsValidationMap(parameters));

        assertThat(feedbacks.size(), is(5));
        assertThat(feedbacks.get(Fields.NAME), is(equalTo("Must be max 15 characters")));
        assertThat(feedbacks.get(Fields.LOCATION), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.DATE), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithNegativeNumberAndNegativeSeats() throws Exception {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciao", "", "102", "", ""};
	    Map<Fields, String> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element, strings[i]);
	        i++;
	    }

	    Map<Fields, String> feedbacks = new Validator().feedbacks(
	        new CreateCourseController().getFieldsValidationMap(parameters));

	    assertThat(feedbacks.size(), is(5));
	    assertThat(feedbacks.get(Fields.SEATS), is(equalTo("Must be less than a threshold (100)!")));
	    assertThat(feedbacks.get(Fields.LOCATION), is(equalTo("Must be defined!")));
	    assertThat(feedbacks.get(Fields.DATE), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesWrongCreateCourseRouteWithPositiveNumberAndPositiveSeats() throws Exception {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"ciao", "", "2", "", ""};
	    Map<Fields, String> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element, strings[i]);
	        i++;
	    }

	    Map<Fields, String> feedbacks = new Validator().feedbacks(
            new CreateCourseController().getFieldsValidationMap(parameters));

        assertThat(feedbacks.size(), is(5));
        assertThat(feedbacks.get(Fields.LOCATION), is(equalTo("Must be defined!")));
        assertThat(feedbacks.get(Fields.DATE), is(equalTo("Must be defined! Must have standard format: (dd.MM.yyyy)!")));
	}

	@Test
	public void handlesCorrectCreateCourseRoute() throws Exception {
	    new CreateCourseController().handles("/course/create");
	    String[] strings = {"sw eng", "sw eng desc", "2", "lugano", "01.01.2000"};
	    Map<Fields, String> parameters = new LinkedHashMap<>();
	    int i=0;
	    for (Fields element : Fields.values()) {
	        parameters.put(element, strings[i]);
	        i++;
	    }

	    Map<Fields, String> feedbacks = new Validator().feedbacks(
	        new CreateCourseController().getFieldsValidationMap(parameters));

	    assertThat(feedbacks.size(), is(5));
	    assertThat(feedbacks.get(Fields.NAME), is(equalTo(null)));
	    assertThat(feedbacks.get(Fields.DESCRIPTION), is(equalTo(null)));
	    assertThat(feedbacks.get(Fields.SEATS), is(equalTo(null)));
	    assertThat(feedbacks.get(Fields.LOCATION), is(equalTo(null)));
	    assertThat(feedbacks.get(Fields.DATE), is(equalTo(null)));
	}

	@Test
    public void dbConnection() throws Exception {
    }

}
