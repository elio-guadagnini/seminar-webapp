package com.app.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.app.controller.CreateCourseController.Fields;
import com.app.entity.date.DateConverter;
import com.app.entity.db.SeminarDbUtil;
import com.app.entity.seminar.Course;
import com.app.entity.seminar.Seminar;
import com.app.entity.seminar.Seminars;
import com.app.entity.validation.DateFormatValidation;
import com.app.entity.validation.LengthValidation;
import com.app.entity.validation.LessThanValidation;
import com.app.entity.validation.NoValidation;
import com.app.entity.validation.NullValidation;
import com.app.entity.validation.NumericValidation;
import com.app.entity.validation.PositivityValidation;
import com.app.entity.validation.Validation;
import com.app.entity.validation.Validator;
import com.app.view.FormSeminarLayout;

public class EditCourseController implements Controller {

    @Override
    public boolean handles(String route) {
        return Pattern.matches("\\/course\\/\\d*", route);
    }

    @Override
    public void execute(Context context, Seminars seminars) throws Exception {
        if ("POST".equals(context.request().getMethod())) {
            checkInputDataSaveSeminar(context);
        }

        if ("GET".equals(context.request().getMethod())) {
            context.response().setContentType("text/html");
            context.response().setCharacterEncoding("UTF-8");

            context.response().getWriter().write(
                new FormSeminarLayout(new LinkedHashMap<Fields, String>())
                    .build()
                    .render());
        }
    }

    public void checkInputDataSaveSeminar(Context context)
        throws ParseException, NullPointerException, IOException, NumberFormatException, SQLException {
        Map<Fields, String> parameters = getKeyParameterMap(context);

        if (new Validator().check(getFieldsValidationMap(parameters))) {
            new SeminarDbUtil(context).saveToDb(
                new Seminar(
                    new Course(
                        parameters.get(Fields.NAME),
                        parameters.get(Fields.DESCRIPTION),
                        new DateConverter()
                        .convertFromStringToDate(
                            parameters.get(Fields.DATE))
                        ),
                    parameters.get(Fields.LOCATION),
                    Integer.parseInt(parameters.get(Fields.SEATS))
                    )
                );

            context.response().sendRedirect("/course");
        } else {
            context.response().getWriter().write(
                new FormSeminarLayout(new Validator()
                    .feedbacks(getFieldsValidationMap(parameters)))
                .build().render()
            );
        }
    }

    public Map<Fields, String> getKeyParameterMap(Context context) {
        Map<Fields, String> result = new LinkedHashMap<>();
        for (Fields element : Fields.values()) {
            String parameter = context.request().getParameter(element.toString().toLowerCase());
            result.put(element, parameter);
        }
        return result;
    }

    public Iterator<Entry<Fields, Validation[]>> getFieldsValidationMap(Map<Fields, String> parameters) {
        Map<Fields, Validation[]> result = new LinkedHashMap<Fields, Validation[]>() {
            {
                put(Fields.NAME, new Validation[] {
                    new NullValidation(parameters.get(Fields.NAME)),
                    new LengthValidation(parameters.get(Fields.NAME), 15)
                    });
                put(Fields.DESCRIPTION, new Validation[] {
                    new NoValidation()
                    });
                put(Fields.SEATS, new Validation[] {
                    new NullValidation(parameters.get(Fields.SEATS)),
                    new NumericValidation(parameters.get(Fields.SEATS)),
                    new PositivityValidation(parameters.get(Fields.SEATS)),
                    new LessThanValidation(parameters.get(Fields.SEATS), 100),
                    new LengthValidation(parameters.get(Fields.SEATS), 3)
                    });
                put(Fields.LOCATION, new Validation[] {
                    new NullValidation(parameters.get(Fields.LOCATION)),
                    new LengthValidation(parameters.get(Fields.LOCATION), 20)
                    });
                put(Fields.DATE, new Validation[] {
                    new NullValidation(parameters.get(Fields.DATE)),
                    new DateFormatValidation(parameters.get(Fields.DATE))
                    });
            }
        };
        return result.entrySet().iterator();
    }

}
