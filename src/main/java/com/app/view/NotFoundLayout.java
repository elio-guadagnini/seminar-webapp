package com.app.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;

public class NotFoundLayout {

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
                            "Ooops!"
                        ),
                        h2(
                            "404 Not Found"
                        ),
                        div(attr("class -> error-details"),
                            "Sorry, amd error as occured, Requested page not found!"
                        )
                    )
                )
            )
        );
    }
}
