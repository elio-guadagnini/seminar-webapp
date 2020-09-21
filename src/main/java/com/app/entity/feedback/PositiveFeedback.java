package com.app.entity.feedback;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.Arrays;
import java.util.Collection;

import com.github.manliogit.javatags.element.Element;
import com.github.manliogit.javatags.element.attribute.Attribute;

public class PositiveFeedback implements Feedback {

    private final String _htmlField;
    private final String _htmlFieldType;

    public PositiveFeedback() {
        this(null, null);
    }

    public PositiveFeedback(String htmlField, String htmlFieldType) {
        _htmlField = htmlField;
        _htmlFieldType = htmlFieldType;
    }

    @Override
    public Collection<Element> getFeedback() {
        return Arrays.asList(span(attr("class -> glyphicon glyphicon-ok form-control-feedback",
            "aria-hidden -> true")
            ),
            span(attr("id -> " + _htmlField + "Status", "class -> sr-only"),
                "(success)"
            ));
    }

    @Override
    public Attribute getFormAttribute() {
        return attr("id -> createCourseForm", "class -> form-horizontal has-feedback",
            "role -> form", "method -> post", "action -> /course");
    }

    @Override
    public String getHtmlField() {
        return _htmlField;
    }

    @Override
    public String getHtmlFieldType() {
        return _htmlFieldType;
    }

    @Override
    public String getHtmlType() {
        return "has-success";
    }

}
