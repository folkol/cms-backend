package com.github.gherkin.persistence;

import com.github.gherkin.Content;

import java.util.Map;

public interface ContentDao {
    void save(Content content);
    void insert(Content content);
    Content retrieve(int id);
    Map<String, Content> retrieveAll();
    void remove(int id);
}
