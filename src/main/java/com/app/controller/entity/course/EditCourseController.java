package com.app.controller.entity.course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.controller.entity.util.EntityUtil;
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

public class EditCourseController implements Controller {

    private static final String TABLE_NAME = "Seminar";
    private static final String TITLE = "Seminar";
    private static final String ACTION = "/course";

    @Override
    public boolean handles(String route) {
        return route.matches("\\/course\\/\\d*");
    }

    @Override
    public void execute(Context context) throws Exception {
        String id = new EntityUtil().getIdFromUri(context.request().getRequestURI());

        Map<String, String[]> parameters = new EntityUtil().getParameterMap(
            context.request().getParameterMap(),
            Seminar.FIELDS
        );

        if ("POST".equals(context.request().getMethod())) {
            checkInputDataSaveSeminar(context, parameters, id);
        }

        if ("GET".equals(context.request().getMethod())) {
            context.response().setContentType("text/html");
            context.response().setCharacterEncoding("UTF-8");

            Map<String, String[]> seminar =
                new EntityMapper(context, TABLE_NAME, Seminar.FIELDS)
                    .findById(id);

            context.response().getWriter().write(
                    new EntityFormLayout(
                        TITLE,
                        seminar,
                        new LinkedHashMap<String, String>(),
                        ACTION + "/" + id)
                .build().render());
        }
    }

    public void checkInputDataSaveSeminar(Context context, Map<String, String[]> parameters, String id)
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
