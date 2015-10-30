package com.github.gherkin;

import com.github.gherkin.persistence.changelog.ChangeLogDao;

import java.util.ArrayList;

public class ChangeLog extends ArrayList<String> {
    ChangeLogDao dao;
    public ChangeLog() {
        dao = new ChangeLogDao();
        this.addAll(dao.retrieveAll());
    }

    @Override
    public boolean add(String s) {
        dao.save(s);
        return super.add(s);
    }
}
