package com.app.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.app.controller.CreateCourseController.Fields;
import com.app.entity.feedback.HtmlFeedback;
import com.github.manliogit.javatags.element.Element;

public class FormSeminarLayout {

    private final Map<Fields, String> _feedbacks;

    public FormSeminarLayout(Map<Fields, String> feedbacks) {
        _feedbacks = feedbacks;
    }

    public Element build() {
        return html5(
            head(
                meta(attr("charset -> utf-8")),
                meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
                meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
                title(
                    "Seminar"
                ),
                text("<!-- Bootstrap core CSS -->"),
                link(attr("href -> /css/bootstrap.min.css", "rel -> stylesheet"))
            ),
            body(
                div(attr("class -> container"),
                    div(attr("class -> row"),
                        div(attr("class -> col-md-6 col-md-offset-3"),
                            h1(attr("class -> page-header text-center"),
                                "Course Form Example"
                            ),
                            form(attr("id -> createCourseForm", "class -> form-horizontal has-feedback",
                                "role -> form", "method -> post", "action -> /course/create"),
                                getHtmlForm()
                            )
                        )
                    )
                ),
                script(attr("src -> /js/jquery.min.js")),
                script(attr("src -> /js/bootstrap.min.js"))
            )
        );
    }

    private Collection<Element> getHtmlForm() {
        Collection<Element> result = new ArrayList<>();
        for (Fields field : Fields.values()) {
            if (_feedbacks.isEmpty()) {
                result.add(new HtmlFeedback(field.toString().toLowerCase(), field._label)
                    .getNeutralFeedback());
            } else {
                result.add(getProperFeedback(field));
            }
        }
        result.add(
            div(attr("class -> form-group"),
                div(attr("class -> col-sm-10 col-sm-offset-2"),
                    input(attr("type -> submit", "class -> btn btn-primary",
                        "id -> submit", "name -> submit",
                        "value -> Send", "role -> button")
                        )
                    )
                )
            );
        return result;
    }

    private Element getProperFeedback(Fields field) {
        try {
            if (!_feedbacks.get(field).equals(null)) {
                return new HtmlFeedback(field.toString().toLowerCase(), field._label, _feedbacks.get(field))
                    .getNegativeFeedback();
            } else {
                return new HtmlFeedback(field.toString().toLowerCase(), field._label)
                    .getPositiveFeedback();
            }
        } catch (NullPointerException e) {
            return new HtmlFeedback(field.toString().toLowerCase(), field._label)
                .getPositiveFeedback();
        }
    }

}
