package com.app.controller;

import com.app.entity.seminar.Seminars;
import com.app.view.NotFoundLayout;

public class NotFoundController implements Controller {

    @Override
    public boolean handles(String route) {
        return true;
    }

    @Override
    public void execute(Context context, Seminars seminars) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        context.response().getWriter().write(new NotFoundLayout().build().render());
    }
}
