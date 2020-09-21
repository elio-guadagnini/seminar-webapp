package com.app.entity.document;

import com.app.entity.seminar.Seminar;

public abstract class Document {

    public Document() {
    }

    protected abstract String getHead(Seminar seminar);
    protected abstract String getBody(Seminar seminar);

    public String getStatement(Seminar seminar) {
        return getHead(seminar) + getBody(seminar);
    }

}
