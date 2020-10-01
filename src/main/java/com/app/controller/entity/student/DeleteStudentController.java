package com.app.controller.entity.student;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.controller.entity.course.EntityMapper;
import com.app.controller.entity.util.EntityUtil;

public class DeleteStudentController implements Controller {

    private static final String ACTION = "/student";
    private static final String TABLE_NAME = "Student";

    @Override
    public boolean handles(String route) {
        return route.matches("\\/student\\/delete\\/\\d*");
    }

    @Override
    public void execute(Context context) throws Exception {
        new EntityMapper(context, TABLE_NAME)
            .removeById(new EntityUtil().getIdFromUri(
                context.request().getRequestURI()));

        context.response().sendRedirect(ACTION);
    }

}
