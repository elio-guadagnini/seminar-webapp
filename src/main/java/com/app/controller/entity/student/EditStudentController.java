package com.app.controller.entity.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.controller.entity.course.EntityMapper;
import com.app.controller.entity.util.EntityUtil;
import com.app.entity.person.Student;
import com.app.entity.validation.LengthValidation;
import com.app.entity.validation.NullValidation;
import com.app.entity.validation.Validation;
import com.app.entity.validation.Validator;
import com.app.view.entity.EntityFormLayout;

public class EditStudentController implements Controller {

    private static final String TABLE_NAME = "Student";
    private static final String TITLE = "Student";
    private static final String ACTION = "/student";

    private final EntityUtil _util = new EntityUtil();

    @Override
    public boolean handles(String route) {
        return route.matches("\\/student\\/\\d*");
    }

    @Override
    public void execute(Context context) throws Exception {
        String id = _util.getIdFromUri(context.request().getRequestURI());

        Map<String, String[]> parameters = _util.getParameterMap(
            context.request().getParameterMap(),
            Student.FIELDS
        );

        if ("POST".equals(context.request().getMethod())) {
            checkInputDataSaveStudent(context, parameters, id);
        }

        if ("GET".equals(context.request().getMethod())) {
            context.response().setContentType("text/html");
            context.response().setCharacterEncoding("UTF-8");

            Map<String, String[]> student =
                new EntityMapper(context, TABLE_NAME, Student.FIELDS)
                    .findById(id);

            context.response().getWriter().write(
                    new EntityFormLayout(
                        TITLE,
                        student,
                        new LinkedHashMap<String, String>(),
                        ACTION + "/" + id)
                .build().render());
        }
    }

    public void checkInputDataSaveStudent(Context context, Map<String, String[]> parameters, String id)
        throws NullPointerException, IOException, NumberFormatException, SQLException {

        if (new Validator().check(getFieldValidation(parameters))) {
            new EntityMapper(context, TABLE_NAME)
                .editById(id, parameters);

            context.response().sendRedirect(ACTION);
        } else {
            context.response().getWriter().write(
                    new EntityFormLayout(
                        TITLE,
                        parameters,
                        new Validator().feedbacks(getFieldValidation(parameters)),
                        ACTION + "/" + id)
                .build().render()
            );
        }
    }

    public Iterator<Entry<String, Validation[]>> getFieldValidation(Map<String, String[]> parameters) {
        Map<String, Validation[]> result = new LinkedHashMap<String, Validation[]>() {
            {
                put(Student.NAME, new Validation[] {
                    new NullValidation(parameters.get(Student.NAME)[0]),
                    new LengthValidation(parameters.get(Student.NAME)[0], 15)
                    });
                put(Student.SURNAME, new Validation[] {
                    new NullValidation(parameters.get(Student.SURNAME)[0]),
                    new LengthValidation(parameters.get(Student.SURNAME)[0], 20)
                    });
            }
        };
        return result.entrySet().iterator();
    }

}