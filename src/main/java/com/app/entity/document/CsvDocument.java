package com.app.entity.document;

import java.util.ArrayList;
import java.util.Collection;

import com.app.entity.seminar.Enrolment;
import com.app.entity.seminar.Seminar;

public class CsvDocument extends Document {

    public CsvDocument() {
    }

    @Override
    protected String getHead(Seminar seminar) {
        return seminar.getCourse().getId() + ";" + seminar.getCourse().getName()
                    + ";" + seminar.getCourse().getDescription() + ";" + seminar.getCourse().getDate() + ";" + seminar.getLocation()
                    + ";" + seminar.getSeatsLeft() + "\n";
    }

    private String getSingleElement(String element) {
        return element;
    }

    private String getSingleElement(Enrolment enrollment) {
        return enrollment.getStudent().getName() + ";" + enrollment.getStudent().getSurname();
    }

    private Collection<String> getEnrollmentsAsStringList(Collection<Enrolment> enrollments) {
        Collection<String> result = new ArrayList<>();
        for (Enrolment enrollment : enrollments) {
            result.add(getSingleElement(enrollment));
        }
        return result;
    }

    private String getMultipleElements(Collection<String> elements) {
        String result = "";
        for (String element : elements)
            result += getSingleElement(element) + "\n";
        return result;
    }

    @Override
    protected String getBody(Seminar seminar) {
        Collection<String> stringList = getEnrollmentsAsStringList(seminar.getEnrolments());
        return getMultipleElements(stringList);
    }

}
