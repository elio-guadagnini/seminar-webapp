package com.app.entity.feedback;

import java.util.Collection;

import com.github.manliogit.javatags.element.Element;
import com.github.manliogit.javatags.element.attribute.Attribute;

public interface Feedback {

    public String getHtmlField();

    public String getHtmlFieldType();

    public String getHtmlType();

    public Collection<Element> getFeedback();

    public Attribute getFormAttribute();

}
