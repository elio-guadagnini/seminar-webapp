package com.app.controller.entity.course;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.controller.entity.util.EntityUtil;
import com.app.converter.DateConverter;
import com.app.entity.seminar.Course;
import com.app.entity.seminar.Seminar;
import com.app.entity.validation.DateFormatValidation;
import com.app.entity.validation.LengthValidation;
import com.app.entity.validation.LessThanValidation;
import com.app.entity.validation.NoValidation;
import com.app.entity.validation.NullValidation;
import com.app.entity.validation.NumericValidation;
import com.app.entity.validation.PositivityValidation;
import com.app.entity.validation.Validation;
import com.app.entity.validation.Validator;
import com.app.view.entity.EntityFormLayout;

public class CreateCourseController implements Controller {

    private static final String TABLE_NAME = "Seminar";
    private static final String TITLE = "Seminar";
    private static final String ACTION = "/course/create";

    @Override
    public boolean handles(String route) {
        return ACTION.equals(route);
    }

    @Override
    public void execute(Context context) throws Exception {
        Map<String, String[]> parameters = new EntityUtil().getParameterMap(
            context.request().getParameterMap(),
            Seminar.FIELDS
        );

        if ("POST".equals(context.request().getMethod())) {
            checkInputDataStoreSeminar(context, parameters);
        }

        if ("GET".equals(context.request().getMethod())) {
            context.response().setContentType("text/html");
            context.response().setCharacterEncoding("UTF-8");

            context.response().getWriter().write(
                    new EntityFormLayout(
                        TITLE,
                        parameters,
                        new LinkedHashMap<String, String>(),
                        ACTION)
                .build().render());
        }
    }

    public void checkInputDataStoreSeminar(Context context, Map<String, String[]> parameters)
        throws ParseException, NullPointerException, IOException, NumberFormatException, SQLException {

        if (new Validator().check(getFieldValidation(parameters))) {
            Seminar seminar = new Seminar(
                new Course(
                    parameters.get(Seminar.NAME)[0],
                    parameters.get(Seminar.DESCRIPTION)[0],
                    new DateConverter()
                        .convertFromStringToDate(parameters.get(Seminar.START)[0])
                    ),
                parameters.get(Seminar.LOCATION)[0],
                Integer.parseInt(parameters.get(Seminar.SEATS)[0])
                );

            new EntityMapper(context, TABLE_NAME, Seminar.FIELDS)
                .add(seminar.getValues());

            context.response().sendRedirect("/course");
        } else {
            context.response().getWriter().write(
                    new EntityFormLayout(
                        TITLE,
                        parameters,
                        new Validator().feedbacks(getFieldValidation(parameters)),
                        ACTION)
                .build().render()
            );
        }
    }

    public Iterator<Entry<String, Validation[]>> getFieldValidation(Map<String, String[]> parameters) {
        Map<String, Validation[]> result = new LinkedHashMap<String, Validation[]>() {
            {
                put(Seminar.NAME, new Validation[] {
                    new NullValidation(parameters.get(Seminar.NAME)[0]),
                    new LengthValidation(parameters.get(Seminar.NAME)[0], 15)
                    });
                put(Seminar.DESCRIPTION, new Validation[] {
                    new NoValidation()
                    });
                put(Seminar.SEATS, new Validation[] {
                    new NullValidation(parameters.get(Seminar.SEATS)[0]),
                    new NumericValidation(parameters.get(Seminar.SEATS)[0]),
                    new PositivityValidation(parameters.get(Seminar.SEATS)[0]),
                    new LessThanValidation(parameters.get(Seminar.SEATS)[0], 100),
                    new LengthValidation(parameters.get(Seminar.SEATS)[0], 3)
                    });
                put(Seminar.LOCATION, new Validation[] {
                    new NullValidation(parameters.get(Seminar.LOCATION)[0]),
                    new LengthValidation(parameters.get(Seminar.LOCATION)[0], 20)
                    });
                put(Seminar.START, new Validation[] {
                    new NullValidation(parameters.get(Seminar.START)[0]),
                    new DateFormatValidation(parameters.get(Seminar.START)[0])
                    });
            }
        };
        return result.entrySet().iterator();
    }
}