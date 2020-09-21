package com.app.controller;

import com.app.entity.seminar.Seminars;
import com.app.view.InternalServerErrorLayout;

public class InternalServerErrorController implements Controller {

    private final Exception _error;

    public InternalServerErrorController(Exception e) {
        _error = e;
    }

    @Override
    public boolean handles(String route) {
        return false;
    }

    @Override
    public void execute(Context context, Seminars seminars) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        context.response().getWriter().write(new InternalServerErrorLayout(_error).build().render());
    }

}
