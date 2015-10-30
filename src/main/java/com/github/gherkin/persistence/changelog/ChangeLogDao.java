package com.github.gherkin.persistence.changelog;

import com.github.gherkin.persistence.GenericDao;

import java.util.List;

public class ChangeLogDao extends GenericDao<ChangeLogEntry, String> {
    private int persistentLogPosition;
    public List<String> get() {
        return retrieveAll();
    }

    public void put(List<String> changeLog) {
        for(String entry : changeLog) {
            save(entry);
        }
    }

    @Override
    protected ChangeLogEntry dataToEntity(String data) {
        return new ChangeLogEntry(data);
    }

    @Override
    protected String entityToData(ChangeLogEntry entity) {
        return entity.toString();
    }
}
