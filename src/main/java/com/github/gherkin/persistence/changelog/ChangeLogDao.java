package com.github.gherkin.persistence.changelog;

import com.github.gherkin.persistence.GenericDao;

import java.util.List;

public class ChangeLogDao extends GenericDao<ChangeLogEntry, String> {

    @Override
    protected ChangeLogEntry dataToEntity(String data) {
        return new ChangeLogEntry(data);
    }

    @Override
    protected String entityToData(ChangeLogEntry entity) {
        return entity.toString();
    }
}
