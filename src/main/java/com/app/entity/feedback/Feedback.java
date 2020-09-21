package com.app.entity.feedback;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;

public class Feedback {

    private final String _htmlFieldName;
    private final String _htmlFieldType;
    private final String _errorMessage;

    public Feedback(String htmlFieldName, String htmlFieldType) {
        this(htmlFieldName, htmlFieldType, null);
    }

    public Feedback(String htmlFieldName, String htmlFieldType, String errorMessage) {
        _htmlFieldName = htmlFieldName;
        _htmlFieldType = htmlFieldType;
        _errorMessage = errorMessage;
    }

    public Element getNeutralFeedback() {
        return
            div(attr("class -> form-group"),
                label(attr("for -> " + _htmlFieldName, "class -> col-sm-2 control-label"),
                    _htmlFieldName),
                div(attr("class -> col-sm-10"),
                    input(attr("type -> " + _htmlFieldType, "class -> form-control",
                        "id -> " + _htmlFieldName, "name -> " + _htmlFieldName,
                        "placeholder -> Course " + _htmlFieldName, "value -> " + _htmlFieldName,
                        "aria-describedby -> " + _htmlFieldName + "Status")
                        )
                    )
                );
    }

    public Element getPositiveFeedback() {
        return
            div(attr("class -> form-group has-success has-feedback"),
                label(attr("for -> " + _htmlFieldName, "class -> col-sm-2 control-label"),
                    _htmlFieldName),
                div(attr("class -> col-sm-10"),
                    input(attr("type -> " + _htmlFieldType, "class -> form-control",
                        "id -> " + _htmlFieldName, "name -> " + _htmlFieldName,
                        "placeholder -> Course " + _htmlFieldName, "value -> " + _htmlFieldName,
                        "aria-describedby -> " + _htmlFieldName + "Status")
                        ),
                    span(attr("class -> glyphicon glyphicon-ok form-control-feedback",
                        "aria-hidden -> true")
                        ),
                    span(attr("id -> " + _htmlFieldName + "Status", "class -> sr-only"),
                        "(success)"
                        )
                    )
                );
    }

    public Element getWarningFeedback() {
        return
            div(attr("class -> form-group has-warning has-feedback"),
                label(attr("for -> " + _htmlFieldName, "class -> col-sm-2 control-label"),
                    _htmlFieldName),
                div(attr("class -> col-sm-10"),
                    input(attr("type -> " + _htmlFieldType, "class -> form-control",
                        "id -> " + _htmlFieldName, "name -> " + _htmlFieldName,
                        "placeholder -> Course " + _htmlFieldName, "value -> " + _htmlFieldName,
                        "aria-describedby -> " + _htmlFieldName + "Status")
                        ),
                    span(attr("class -> glyphicon glyphicon-warning-sign form-control-feedback",
                        "aria-hidden -> true")
                        ),
                    span(attr("id -> " + _htmlFieldName + "Status", "class -> sr-only"),
                        "(warning)"
                        )
                    )
                );
    }

    public Element getNegativeFeedback() {
        return
            div(attr("class -> form-group has-error has-feedback"),
                label(attr("for -> " + _htmlFieldName, "class -> col-sm-2 control-label"),
                    _htmlFieldName),
                div(attr("class -> col-sm-10"),
                    input(attr("type -> " + _htmlFieldType, "class -> form-control",
                        "id -> " + _htmlFieldName, "name -> " + _htmlFieldName,
                        "placeholder -> Course " + _htmlFieldName, "value -> " + _htmlFieldName,
                        "aria-describedby -> " + _htmlFieldName + "Status")
                        ),
                    span(attr("id -> " + _htmlFieldName, "class -> help-block"),
                        "The value is not correct: " + _errorMessage
                        ),
                    span(attr("class -> glyphicon glyphicon-remove form-control-feedback",
                        "aria-hidden -> true")
                        ),
                    span(attr("id -> " + _htmlFieldName + "Status", "class -> sr-only"),
                        "(error)"
                        )
                    )
                );
    }

}
