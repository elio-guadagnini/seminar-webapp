package com.app.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;

public class LayoutTest {

    @Test
    public void html() {

        XPath xPath = XPathFactory.newInstance().newXPath();

        System.out.println(xPath);

        assertThat("", is(equalTo("")));
    }

}
