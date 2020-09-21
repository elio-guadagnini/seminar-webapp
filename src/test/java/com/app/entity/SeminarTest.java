package com.app.entity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.app.entity.document.CsvDocument;
import com.app.entity.document.DocumentUtil;
import com.app.entity.document.HtmlDocument;
import com.app.entity.person.Student;
import com.app.entity.seminar.Course;
import com.app.entity.seminar.Enrolment;
import com.app.entity.seminar.Seminar;

public class SeminarTest {

	@Test
	public void htmlStatementTest() {
	    GregorianCalendar calendar = new GregorianCalendar();
	    String currentDate = calendar.get(GregorianCalendar.DAY_OF_MONTH) + "/"
	                                    + calendar.get(GregorianCalendar.MONTH) + "/"
	                                    + calendar.get(GregorianCalendar.YEAR);
		Seminar swEngineering = new Seminar(new Course("Sw is important.", 1, "by ...", calendar), "Lugano", 10);
		swEngineering.addEnrolment(new Enrolment(new Student("Elio", "Guadagnini")));
		swEngineering.addEnrolment(new Enrolment(new Student("Elio", "Guadagnino")));

		String html = new HtmlDocument().getStatement(swEngineering);

		assertThat(html, equalTo("<html>\n<head>\n\t<title>Sw is important. 1</title>\n</head>\n"
				+ "<body>\n\t<div>Sw is important.:</div>\n\t<ul>\n\t\t<li>by ...</li>\n\t\t<li>"+currentDate+"</li>\n\t\t<li>Lugano</li>\n\t\t<li>8</li>\n\t</ul>"
						+ "\n\t<div>partecipanti:</div>\n\t<ul>"
							+ "\n\t\t<li>Elio Guadagnini</li>"
							+ "\n\t\t<li>Elio Guadagnino</li>"
						+ "\n\t</ul>\n</body>\n</html>"));
	}

	@Test
	public void csvStatementTest() {
	    GregorianCalendar calendar = new GregorianCalendar();
        String currentDate = calendar.get(GregorianCalendar.DAY_OF_MONTH) + "/"
                                        + calendar.get(GregorianCalendar.MONTH) + "/"
                                        + calendar.get(GregorianCalendar.YEAR);
		Seminar swEngineering = new Seminar(new Course("Sw is important.", 1, "by ...", calendar), "Lugano", 10);
		swEngineering.addEnrolment(new Enrolment(new Student("Elio", "Guadagnini")));
		swEngineering.addEnrolment(new Enrolment(new Student("Elio", "Guadagnino")));

		String csv = new CsvDocument().getStatement(swEngineering);

		assertThat(csv, equalTo("1;Sw is important.;by ...;"+currentDate+";Lugano;8\n"
				+ "Elio;Guadagnini\n"
				+ "Elio;Guadagnino\n"));
	}

	@Test
	public void printCsvTest() {
	    GregorianCalendar calendar = new GregorianCalendar();
		Seminar swEngineering = new Seminar(new Course("Sw is important.", 1, "by ...", calendar), "Lugano", 10);
		swEngineering.addEnrolment(new Enrolment(new Student("Elio", "Guadagnini")));
		swEngineering.addEnrolment(new Enrolment(new Student("Elio", "Guadagnino")));
		String csv = new CsvDocument().getStatement(swEngineering);

		String path = "/home/dev/Desktop/"+swEngineering.getName()+".csv";
		new DocumentUtil().print(csv, path);

		assertThat(Files.exists(Paths.get(path)), is(true));
	}

}
