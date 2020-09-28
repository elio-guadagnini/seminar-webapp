package com;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import com.app.controller.Controller;
import com.app.controller.InternalServerErrorController;
import com.app.controller.NotFoundController;
import com.app.controller.course.CreateCourseController;
import com.app.controller.course.DeleteCourseController;
import com.app.controller.course.EditCourseController;
import com.app.controller.course.CourseListController;

public class ControllerFactory {

    public List<Controller> create() {
        return new ArrayList<>(
            asList(
                new CourseListController(),
                new CreateCourseController(),
                new EditCourseController(),
                new DeleteCourseController(),
                new NotFoundController()
           ));
    }

    public Controller internalError(Exception e) {
        return new InternalServerErrorController(e);
    }
}
