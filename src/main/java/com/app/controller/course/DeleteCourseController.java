package com.app.controller.course;

import com.app.controller.Context;
import com.app.controller.Controller;
import com.app.entity.db.SeminarDb;

public class DeleteCourseController implements Controller {

    private final ControllerCourseUtil _util = new ControllerCourseUtil();
    private String _id;

    @Override
    public boolean handles(String route) {
        return route.matches("\\/course\\/delete\\/\\d*");
    }

    @Override
    public void execute(Context context) throws Exception {
        _id = _util.getIdFromUri(context);

        new SeminarDb(context).delete("id -> "+_id);

        context.response().sendRedirect("/course");
    }

}
