package com;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import com.app.controller.Controller;
import com.app.controller.CreateCourseController;
import com.app.controller.EditCourseController;
import com.app.controller.InternalServerErrorController;
import com.app.controller.NotFoundController;
import com.app.controller.ShowCourseListController;

public class ControllerFactory {

    public List<Controller> create() {
        return new ArrayList<>(
            asList(
                new ShowCourseListController(),
                new CreateCourseController(),
                new EditCourseController(),
                new NotFoundController()
            ));
    }

    public Controller internalError(Exception e) {
        return new InternalServerErrorController(e);
    }
}
