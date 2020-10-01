package com.app.controller.entity.student;

import java.util.List;
import java.util.Map;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.controller.entity.course.EntityMapper;
import com.app.entity.person.Student;
import com.app.view.entity.EntityListLayout;

public class StudentListController implements Controller {

    private final static String TABLE_NAME = "Student";
    private final static String ACTION = "/student";
    private final static String TITLE = "Student";

    @Override
    public boolean handles(String route) {
        return ACTION.equals(route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        List<Map<String, String>> students =
            new EntityMapper(context, TABLE_NAME, Student.FIELDS)
                .findAll();

        context.response().getWriter().write(
                new EntityListLayout(TITLE, ACTION, students)
            .build().render());
    }

}
