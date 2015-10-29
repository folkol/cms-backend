package com.github.gherkin.persistance;

import com.github.gherkin.Content;

public interface Persistance {
    void save(Content content);
    Content retrieve(int id);
    void remove(int id);
}
