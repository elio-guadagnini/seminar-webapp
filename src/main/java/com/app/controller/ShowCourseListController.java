package com.app.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.entity.db.SeminarDbUtil;
import com.app.entity.seminar.Seminars;
import com.app.view.ShowCourseListLayout;

public class ShowCourseListController implements Controller {

    private ResultSet _seminars1;

    @Override
    public boolean handles(String route) {
        return ("/course".equals(route)
            || "/course/".equals(route)
            || "/".equals(route));
    }

    @Override
    public void execute(Context context, Seminars seminars) throws SQLException, IOException  {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        _seminars1 = new SeminarDbUtil(context).queryToDb("*");

        context.response().getWriter().write(new ShowCourseListLayout(_seminars1).build().render());
    }
}
