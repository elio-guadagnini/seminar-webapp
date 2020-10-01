package com;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import com.app.controller.Controller;
import com.app.controller.InternalServerErrorController;
import com.app.controller.NotFoundController;
import com.app.controller.entity.course.CourseListController;
import com.app.controller.entity.course.CreateCourseController;
import com.app.controller.entity.course.DeleteCourseController;
import com.app.controller.entity.course.EditCourseController;
import com.app.controller.entity.student.CreateStudentController;
import com.app.controller.entity.student.DeleteStudentController;
import com.app.controller.entity.student.EditStudentController;
import com.app.controller.entity.student.StudentListController;

public class ControllerFactory {

    public List<Controller> create() {
        return new ArrayList<>(
            asList(
                new CourseListController(),
                new CreateCourseController(),
                new EditCourseController(),
                new DeleteCourseController(),
                new StudentListController(),
                new CreateStudentController(),
                new EditStudentController(),
                new DeleteStudentController(),
                new NotFoundController()
           ));
    }

    public Controller internalError(Exception e) {
        return new InternalServerErrorController(e);
    }
}
