package com.app.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.app.entity.db.SeminarDbUtil;

public class CrudTest {

    @Test
    public void insert() throws Exception {

        String result = new SeminarDbUtil(null).insertSql();

        assertThat(result, is(equalTo("INSERT INTO Seminar (name, description, location, totalSeats, start) VALUES (?, ?, ?, ?, ?);")));
    }

    @Test
    public void update() throws Exception {

        String result = new SeminarDbUtil(null).updateSql("id = 1000", "name = 'elio'");

        assertThat(result, is(equalTo("UPDATE Seminar SET id = 1000 WHERE name = 'elio';")));
    }

    @Test
    public void delete() throws Exception {

        String result = new SeminarDbUtil(null).deleteSql("name = 'elio'");

        assertThat(result, is(equalTo("DELETE FROM Seminar WHERE name = 'elio';")));
    }

    @Test
    public void select() throws Exception {

        String result = new SeminarDbUtil(null).selectSql("*");

        assertThat(result, is(equalTo("SELECT * FROM Seminar;")));
    }

}
