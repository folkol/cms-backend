package com.github.gherkin.persistence;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "content")
public class ContentEntity {
    @Id
    @Column(name = "id")
    private int id;

    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="attributes")
    private Map<String, String> content;

    public ContentEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}
