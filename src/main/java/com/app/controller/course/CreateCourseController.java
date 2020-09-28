package com.app.controller.course;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.converter.DateConverter;
import com.app.converter.EnumConverter;
import com.app.entity.db.SeminarDb;
import com.app.entity.seminar.Course;
import com.app.entity.seminar.Seminar;
import com.app.entity.validation.Validator;
import com.app.view.entity.EntityFormLayout;

public class CreateCourseController implements Controller {

    public enum Fields {
        NAME, DESCRIPTION, SEATS, LOCATION, START;
    }

    private final ControllerCourseUtil _util = new ControllerCourseUtil();
    private final String _title = "Seminar";
    private final String _action = "/course/create";

    @Override
    public boolean handles(String route) {
        return "/course/create".equals(route);
    }

    @Override
    public void execute(Context context) throws Exception {
        Map<String, String[]> parameters = _util.getParameterMap(
                context.request().getParameterMap(),
                new EnumConverter().convertEnumsToStrings(Fields.values())
            );

        if ("POST".equals(context.request().getMethod())) {
            checkInputDataStoreSeminar(context, parameters);
        }

        if ("GET".equals(context.request().getMethod())) {
            context.response().setContentType("text/html");
            context.response().setCharacterEncoding("UTF-8");

            context.response().getWriter().write(
                new EntityFormLayout(
                    _title,
                    parameters,
                    new LinkedHashMap<String, String>(),
                    _action)
                .build().render());
        }
    }

    public void checkInputDataStoreSeminar(Context context, Map<String, String[]> parameters)
        throws ParseException, NullPointerException, IOException, NumberFormatException, SQLException {

        if (new Validator().check(_util.getFieldValidation(parameters))) {
            new SeminarDb(context).insert(
                new Seminar(
                    new Course(
                        _util.getSingleParameter(parameters, Fields.NAME),
                        _util.getSingleParameter(parameters, Fields.DESCRIPTION),
                        new DateConverter()
                            .convertFromStringToDate(_util.getSingleParameter(parameters, Fields.START))
                        ),
                    _util.getSingleParameter(parameters, Fields.LOCATION),
                    Integer.parseInt(_util.getSingleParameter(parameters, Fields.SEATS))
                    )
                );

            context.response().sendRedirect("/course");
        } else {
            context.response().getWriter().write(
                new EntityFormLayout(
                    _title,
                    parameters,
                    new Validator().feedbacks(_util.getFieldValidation(parameters)),
                    _action)
                .build().render()
            );
        }
    }
}
