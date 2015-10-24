package com.github.gherkin;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Produces(MediaType.APPLICATION_JSON)
@Path("content")
public class ContentResource {
    static Map<Integer, Article> articles = new HashMap<>();
    static AtomicInteger nextId = new AtomicInteger();

    @GET
    public Map<Integer, Article> fetchAll() {
        return articles;
    }

    @GET
    @Path("{id}")
    public Article fetch(@PathParam("id") int id) {
        return articles.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Article insert(Article article) {
        if(article.getTitle() == null && article.getText() == null)
            throw new IllegalArgumentException("missing title and body");

        article.setId(nextId.incrementAndGet());
        articles.put(article.getId(), article);

        return article;
    }
}
