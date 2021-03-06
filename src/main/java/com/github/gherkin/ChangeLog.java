package com.github.gherkin;

import com.github.gherkin.persistence.changelog.ChangeLogDao;
import com.google.inject.Inject;

import java.util.ArrayList;

public class ChangeLog extends ArrayList<String> {
    @Inject
    ChangeLogDao dao;
    public ChangeLog() {
        this.addAll(dao.retrieveAll());
    }

    @Override
    public boolean add(String s) {
        dao.save(s);
        return super.add(s);
    }
}
