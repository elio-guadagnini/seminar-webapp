package com.app.controller.entity.course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.entity.seminar.Seminar;
import com.app.view.entity.EntityListLayout;

public class CourseListController implements Controller {

    private static final String TABLE_NAME = "Seminar";
    private static final String TITLEPAGE = "Seminar";
    private static final String ACTION = "/course";

    @Override
    public boolean handles(String route) {
        return ("/course".equals(route) || "".equals(route));
    }

    @Override
    public void execute(Context context) throws SQLException, IOException  {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        List<Map<String, String>> seminars =
            new EntityMapper(context, TABLE_NAME, Seminar.FIELDS)
                .findAll();

        context.response().getWriter().write(
                new EntityListLayout(TITLEPAGE,
                                     ACTION,
                                     seminars)
            .build().render());
    }
}
