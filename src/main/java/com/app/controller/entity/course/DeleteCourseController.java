package com.app.controller.entity.course;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.controller.entity.util.EntityUtil;

public class DeleteCourseController implements Controller {

    private static final String TABLE_NAME = "Seminar";
    private static final String ACTION = "/course";

    @Override
    public boolean handles(String route) {
        return route.matches("\\/course\\/delete\\/\\d*");
    }

    @Override
    public void execute(Context context) throws Exception {
        new EntityMapper(context, TABLE_NAME)
            .removeById(new EntityUtil().getIdFromUri(
                context.request().getRequestURI()));

        context.response().sendRedirect(ACTION);
    }

}
