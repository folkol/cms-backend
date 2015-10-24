package com.github.gherkin;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Path("content")
public class ContentResource {
    static List<Article> articles = new ArrayList<Article>();
    static AtomicInteger nextId = new AtomicInteger();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> fetchAll() {
        return articles;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Article insert(Article article) {
        if(article.getTitle() == null && article.getText() == null)
            throw new IllegalArgumentException("missing title and body");

        article.setId(nextId.incrementAndGet());
        articles.add(article);

        return article;
    }
}
