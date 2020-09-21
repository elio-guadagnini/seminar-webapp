package com.app.entity.seminar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Seminars {

    private final List<Seminar> _seminars;

    public Seminars() {
        _seminars = new ArrayList<>();
    }

    public void addSeminar(Seminar seminar) {
        _seminars.add(seminar);
    }

    public Seminar getSeminar(int index) {
        return _seminars.get(index);
    }

    public int numberOfSeminars() {
        return _seminars.size();
    }

    public Iterator<Seminar> getIterator() {
        return _seminars.iterator();
    }

    @Override
    public String toString() {
        String result = "";
        for (Seminar seminar : _seminars)
            result = seminar.toString();
        return result;
    }

}
