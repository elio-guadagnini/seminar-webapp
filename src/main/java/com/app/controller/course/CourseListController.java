package com.app.controller.course;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.entity.db.SeminarDb;
import com.app.view.entity.EntityListLayout;

public class CourseListController implements Controller {

    private final String _title = "Seminar";
    private final String _action = "/course";
    private ResultSet _rs;

    @Override
    public boolean handles(String route) {
        return ("/course".equals(route) || "/".equals(route));
    }

    @Override
    public void execute(Context context) throws SQLException, IOException  {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        _rs = new SeminarDb(context).select("*", null);

        context.response().getWriter().write(
            new EntityListLayout(_title, _action, _rs)
            .build().render());
    }
}
