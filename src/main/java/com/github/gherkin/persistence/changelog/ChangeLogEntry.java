package com.github.gherkin.persistence.changelog;

import javax.persistence.*;

@Entity
@Table(name = "changelog")
public class ChangeLogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int ContentId;

    public ChangeLogEntry(String ContentId) {
        id = null;
        this.ContentId = Integer.parseInt(ContentId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContentId() {
        return ContentId;
    }

    public void setContentId(int contentId) {
        ContentId = contentId;
    }

    @Override
    public String toString() {
        return Integer.toString(ContentId);
    }
}
