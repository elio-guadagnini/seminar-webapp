package com.app.entity.document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.app.entity.seminar.Enrolment;
import com.app.entity.seminar.Seminar;

public class HtmlDocument extends Document {

    private String getSorroundings(String element, String preceding, String succeeding) {
        return preceding + element + succeeding;
    }

	private String getTitle(String name, int id) {
		return getSorroundings(
		                    name + " " + id,
		                    "\n\t<title>",
		                    "</title>\n");
	}

	@Override
	protected String getHead(Seminar seminar) {
		return "<html>\n" + getSorroundings(
    		                    getTitle(seminar.getCourse().getName(), seminar.getCourse().getId()),
    		                    "<head>",
    		                    "</head>\n");
	}

    private String getSingleElement(String element) {
        return getSorroundings(element, "<li>", "</li>");
    }

    private String getSingleElement(Enrolment enrollment) {
        return enrollment.getStudent().getFullName();
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
            result += "\n\t\t" + getSingleElement(element);
        return result + "\n\t";
    }

    @Override
    protected String getBody(Seminar seminar) {
        Collection<String> stringList = getEnrollmentsAsStringList(seminar.getEnrolments());
        return getSorroundings(
                            getSorroundings(seminar.getCourse().getName(), "\n\t<div>", ":</div>\n\t")
                            + getSorroundings(
                                getMultipleElements(Arrays.asList(seminar.getCourse().getDescription(), seminar.getCourse().getDate().toString(), seminar.getLocation(), String.valueOf(seminar.getSeatsLeft()))),
                                "<ul>", "</ul>\n\t")
                            + getSorroundings("partecipanti", "<div>", ":</div>\n\t")
                            + getSorroundings(
                                getMultipleElements(stringList),
                                "<ul>", "</ul>\n"),
                            "<body>",
                            "</body>\n") + "</html>";
    }

}
