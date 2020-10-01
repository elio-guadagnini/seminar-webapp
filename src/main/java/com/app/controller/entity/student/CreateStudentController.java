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

public class CreateStudentController implements Controller {

    private static final String TABLE_NAME = "Student";
    private static final String TITLE = "Create Student";
    private static final String ACTION = "/student/create";

    @Override
    public boolean handles(String route) {
        return ACTION.equals(route);
    }

    @Override
    public void execute(Context context) throws Exception {
        Map<String, String[]> parameters = new EntityUtil().getParameterMap(
            context.request().getParameterMap(),
            Student.FIELDS
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
        throws NullPointerException, IOException, NumberFormatException, SQLException {

        if (new Validator().check(getFieldValidation(parameters))) {
            Student student = new Student(
                parameters.get(Student.NAME)[0],
                parameters.get(Student.SURNAME)[0]
                );

            new EntityMapper(context, TABLE_NAME, Student.FIELDS)
                .add(student.getValues());

            context.response().sendRedirect("/student");
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
