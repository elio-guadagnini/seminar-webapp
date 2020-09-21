package com.app.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;

public class InternalServerErrorLayout {

    private final Exception _error;

    public InternalServerErrorLayout(Exception e) {
        _error = e;
    }

    public Element build() {
        return html5(
            head(
                meta(attr("charset -> utf-8")),
                meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
                meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
                title(
                    "helloWorld!"
                ),
                text("<!-- Bootstrap core CSS -->"),
                link(attr("href -> css/bootstrap.min.css", "rel -> stylesheet"))
            ),
            body(
                div(attr("class -> container"),
                    div(attr("class -> row")),
                    div(attr("class -> error-template"),
                        h1(
                            "This page isn't working!"
                        ),
                        h2(
                            "500 : Internal Server Error"
                        ),
                        div(attr("class -> error-details"),
                            _error.toString()
                        )
                    )
                )
            )
        );
    }
}
