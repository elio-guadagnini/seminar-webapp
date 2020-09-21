package com.app.controller;

import com.app.entity.seminar.Seminars;
import com.app.view.ShowCourseListLayout;

public class ShowCourseListController implements Controller {

    @Override
    public boolean handles(String route) {
        return ("/course".equals(route)
            || "/course/".equals(route)
            || "/".equals(route));
    }

    @Override
    public void execute(Context context, Seminars seminars) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        context.response().getWriter().write(new ShowCourseListLayout(seminars).build().render());
    }
}
