package com.app.entity.feedback;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.Arrays;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public class HtmlFeedback {

    private final String _fieldName;
    private final String _fieldType;
    private final String _errorMessage;

    public HtmlFeedback(String htmlFieldName, String htmlFieldType) {
        this(htmlFieldName, htmlFieldType, null);
    }

    public HtmlFeedback(String htmlFieldName, String htmlFieldType, String errorMessage) {
        _fieldName = htmlFieldName;
        _fieldType = htmlFieldType;
        _errorMessage = errorMessage;
    }

    public Element getNeutralFeedback() {
        return
            div(attr("class -> form-group"),
                label(attr("for -> " + _fieldName, "class -> col-sm-2 control-label"),
                    _fieldName),
                div(attr("class -> col-sm-10"),
                    input(attr("type -> " + _fieldType, "class -> form-control",
                        "id -> " + _fieldName, "name -> " + _fieldName,
                        "placeholder -> Course " + _fieldName, "value -> " + _fieldName,
                        "aria-describedby -> " + _fieldName + "Status")
                        )
                    )
                );
    }

    private Element htmlLayout(String divHeader, String glyphicon, String divFooter) {
        List<Element> result = Arrays.asList(
                div(attr("class -> form-group " + divHeader + " has-feedback"),
                    label(attr("for -> " + _fieldName, "class -> col-sm-2 control-label"),
                        _fieldName),
                    div(attr("class -> col-sm-10"),
                        input(attr("type -> " + _fieldType, "class -> form-control",
                            "id -> " + _fieldName, "name -> " + _fieldName,
                            "placeholder -> Course " + _fieldName, "value -> " + _fieldName,
                            "aria-describedby -> " + _fieldName + "Status")
                            ),
                        span(attr("class -> glyphicon " + glyphicon + " form-control-feedback",
                            "aria-hidden -> true")
                            ),
                        span(attr("id -> " + _fieldName + "Status", "class -> sr-only"),
                            divFooter
                            ),
                        htmlErrorMessage()
                        )
                    )
                );
        return group(result);
    }

    private Element htmlErrorMessage() {
        try {
            if (!_errorMessage.isEmpty()) {
                return
                    span(attr("id -> " + _fieldName + "Status", "class -> help-block"),
                        "The value is not correct: " + _errorMessage
                        );
            } else {
                return span(attr("id -> " + _fieldName + "Status", "class -> help-block"),
                        ""
                        );
            }
        } catch (NullPointerException e) {
            return span(attr("id -> " + _fieldName + "Status", "class -> help-block"),
                    ""
                    );
        }
    }

    public Element getPositiveFeedback() {
        return htmlLayout("has-success", "glyphicon-ok", "(success)");
    }

    public Element getWarningFeedback() {
        return htmlLayout("has-warning", "glyphicon-warning-sign", "(warning)");
    }

    public Element getNegativeFeedback() {
        return htmlLayout("has-error", "glyphicon-remove", "(error)");
    }

}
