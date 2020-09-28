package com.app.controller.course;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.controller.course.CreateCourseController.Fields;
import com.app.converter.EnumConverter;
import com.app.entity.db.SeminarDb;
import com.app.entity.validation.Validator;
import com.app.view.entity.EntityFormLayout;

public class EditCourseController implements Controller {

    private String _id;
    private final String _title = "Seminar";
    private String _action = "/course/";
    private final ControllerCourseUtil _util = new ControllerCourseUtil();

    @Override
    public boolean handles(String route) {
        return route.matches("\\/course\\/\\d*");
    }

    @Override
    public void execute(Context context) throws Exception {
        _id = _util.getIdFromUri(context);
        _action += _id;

        Map<String, String[]> parameters = _util.getParameterMap(context.request().getParameterMap(),
            new EnumConverter().convertEnumsToStrings(Fields.values()));

        if ("POST".equals(context.request().getMethod())) {
            checkInputDataSaveSeminar(context, parameters);
        }

        if ("GET".equals(context.request().getMethod())) {
            context.response().setContentType("text/html");
            context.response().setCharacterEncoding("UTF-8");

            ResultSet rs = new SeminarDb(context).select("*", "id -> "+_id);
            parameters = _util.getParameterMap(
                rs,
                new EnumConverter().convertEnumsToStrings(Fields.values())
            );

            context.response().getWriter().write(
                new EntityFormLayout(
                    _title,
                    parameters,
                    new LinkedHashMap<String, String>(),
                    _action)
                .build().render());
        }
    }

    public void checkInputDataSaveSeminar(Context context, Map<String, String[]> parameters)
        throws NullPointerException, IOException, NumberFormatException, SQLException {

        if (new Validator().check(_util.getFieldValidation(parameters))) {
            new SeminarDb(context).update(set(parameters), "id -> "+_id);

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

    private String set(Map<String, String[]> parameters) {
        String result = "";
        for (Entry<String, String[]> param : parameters.entrySet()) {
            result += param.getKey() + " -> " + param.getValue()[0] + ",";
        }
        return result.substring(0, result.length()-1);
    }

}
