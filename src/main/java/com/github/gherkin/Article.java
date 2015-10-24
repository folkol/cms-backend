package com.github.gherkin;

public class Article {
    private int id = -1;
    private String title;
    private String text;

    public Article() {

    }

    public Article(String text, String title) {
        this.text = text;
        this.title = title;
    }

    public Article(int id, String text, String title) {
        this.id = id;
        this.text = text;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
