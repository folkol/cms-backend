package com.github.gherkin.persistence;

import com.github.gherkin.Content;

public interface ContentDao {
    void save(Content content);
    void insert(Content content);
    Content retrieve(int id);
    void remove(int id);
}
