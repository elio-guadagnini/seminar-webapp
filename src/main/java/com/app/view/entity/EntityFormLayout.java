package com.app.view.entity;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.app.entity.feedback.HtmlFeedback;
import com.github.manliogit.javatags.element.Element;

public class EntityFormLayout {

    private final Map<String, String[]> _parameters;
    private final Map<String, String> _feedbacks;
    private final String _action;
    private final String _title;

    public EntityFormLayout(String title, Map<String, String[]> parameters,
        Map<String, String> feedbacks, String action) {
        _parameters = parameters;
        _feedbacks = feedbacks;
        _action = action;
        _title = title;
    }

    public Element build() {
        return html5(
            head(
                meta(attr("charset -> utf-8")),
                meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
                meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
                title(
                    _title
                ),
                text("<!-- Bootstrap core CSS -->"),
                link(attr("href -> /css/bootstrap.min.css", "rel -> stylesheet"))
            ),
            body(
                div(attr("class -> container"),
                    div(attr("class -> row"),
                        div(attr("class -> col-md-6 col-md-offset-3"),
                            h1(attr("class -> page-header text-center"),
                                "Entity Form"
                            ),
                            form(attr("id -> createCourseForm", "class -> form-horizontal has-feedback",
                                    "role -> form", "method -> post", "action -> " + _action),
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

        for (Entry<String, String[]> param : _parameters.entrySet()) {
            String parameter = param.getValue()[0];
            result.add(getProperFeedback(param.getKey(), parameter));
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

    private Element getProperFeedback(String fieldName, String fieldValue) {
        if (_feedbacks.isEmpty()) {
           return new HtmlFeedback(fieldName, fieldValue)
                .getNeutralFeedback();
        } else if (_feedbacks.containsKey(fieldName)) {
            return new HtmlFeedback(fieldName, fieldValue, _feedbacks.get(fieldName))
                .getNegativeFeedback();
        } else {
            return new HtmlFeedback(fieldName, fieldValue)
                .getPositiveFeedback();
        }
    }
}
