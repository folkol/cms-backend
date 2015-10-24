package com.github.gherkin;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Path("content")
public class ContentResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> fetchAll() {
        Article content1 = new Article("hej", "hello");
        Article content2 = new Article("eluveite", "havoc");


        return Arrays.asList(content1, content2);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Article insert(Article article) {
        return article;
    }
}
