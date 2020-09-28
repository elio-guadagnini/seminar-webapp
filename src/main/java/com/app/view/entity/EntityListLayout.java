package com.app.view.entity;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.github.manliogit.javatags.element.Element;

public class EntityListLayout {

    private final String _title;
    private final String _baseAction;
    private final ResultSet _seminars;

    public EntityListLayout(String title, String baseAction, ResultSet seminars) {
        _title = title;
        _baseAction = baseAction;
        _seminars = seminars;
    }

    public Element build() throws SQLException {
        return html5(
            head(
                meta(attr("charset -> utf-8")),
                meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
                meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
                title(
                    _title
                ),
                text("<!-- Bootstrap core CSS -->"),
                link(attr("href -> /css/bootstrap.min.css?v=1.0.0", "rel -> stylesheet", "media -> screen")),
                link(attr("href -> /css/custom.css?v=1.0.0", "rel -> stylesheet")),
                link(attr("href -> /css/app.css?v=1.0.0", "rel -> stylesheet"))
//                script(attr("src -> https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js")),
//                script(attr("src -> https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"))
            ),
            body(
                div(attr("class -> navbar navbar-default navbar-fixed-top"),
                    div(attr("class -> container"),
                        div(attr("class -> navbar-header"),
                            a(attr("href -> /", "class -> navbar-brand"),
                                _title
                                ),
                            button(attr("class -> navbar-toggle", "type -> button",
                                "data-toggle -> collapse", "data-target -> #navbar-main"),
                                span(attr("class -> icon-bar")),
                                span(attr("class -> icon-bar")),
                                span(attr("class -> icon-bar"))
                                )
                            ),
                        div(attr("class -> navbar-collapse collapse", "id -> navbar-main"),
                            ul(attr("class -> nav navbar-nav navbar-right"),
                                li(attr("class -> dropdown"),
                                    a(attr("class -> dropdown-toggle", "data-toggle -> dropdown",
                                        "href -> #", "id -> download", "aria-expanded -> false"),
                                        text("Account "),
                                        span(attr("class -> caret"))
                                        ),
                                    ul(attr("class -> dropdown-menu", "aria-labelledby -> download"),
                                        li(
                                            a(attr("href -> /"),
                                                "settings"
                                                )),
                                        li(attr("class -> divider")),
                                        li(
                                            a(attr("href -> /"),
                                                "logout"
                                                ))
                                        )
                                    )
                                )
                            )
                        )
                    ),
                div(attr("class -> container"),
                    div(attr("class -> page-header", "id -> banner"),
                        div(attr("class -> row"),
                            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                                h1(
                                    _title
                                    ),
                                p(attr("class -> lead"),
                                    "Manage your "+_title+"s!"
                                    )
                                )
                            ),
                        div(attr("class -> row"),
                            div(attr("class -> col-lg-2 col-md-2 col-sm-3"),
                                div(attr("class -> list-group table-of-contents"),
                                    a(attr("class -> list-group-item", "href -> "+_baseAction),
                                        "List"
                                        ),
                                    a(attr("class -> list-group-item", "href -> "+_baseAction+"/create"),
                                        "Create"
                                        )
                                    )
                                ),
                            div(attr("class -> col-lg-8 col-md-8 col-sm-9"),
                                table(attr("class -> table table-striped"),
                                    thead(
                                        tr(
                                            th("name"),
                                            th("description"),
                                            th("location"),
                                            th("totalSeats"),
                                            th("start")
                                            )
                                        ),
                                    tbody(
                                        getHtmlSeminars()
                                        )
                                    )
                                )
                            )
                        ),
                    footer(
                        div(attr("class -> row"),
                            div(attr("class -> col-lg-12"),
                                p(
                                    a(attr("href -> http://bootswatch.com/cerulean", "rel -> nofollow"),
                                        "Cerulean theme"
                                        )
                                    ),
                                p(
                                    text("Code released under the "),
                                    a(attr("href -> https://github.com/thomaspark/bootswatch/blob/gh-pages/LICENSE"),
                                        "MIT Lisense"
                                        )
                                    ),
                                p("GmTechnologies")
                                )
                            )
                        )
                    ),
                script(attr("src -> /js/jquery.min.js")),
                script(attr("src -> /js/bootstrap.min.js"))
            )
        );
    }

    private Iterable<Element> getHtmlSeminars() throws SQLException {
        Collection<Element> htmlSeminars = new ArrayList<>();

        while (_seminars.next()) {
            htmlSeminars.add(
                tr(
                    td(
                        a(attr("href -> "+_baseAction+"/"+_seminars.getString(1)),
                            _seminars.getString(2)
                            )
                        ),
                    td(_seminars.getString(3)),
                    td(_seminars.getString(4)),
                    td(_seminars.getString(5)),
                    td(_seminars.getString(6)),
                    td(
                        a(attr("href -> "+_baseAction+"/delete/"+_seminars.getString(1), "class -> btn btn-primary btn-xs",
                            "role -> button", "aria-pressed -> true"),
                            "delete"
                            )
                        )
                )
            );
        }
        return htmlSeminars;
    }

}
