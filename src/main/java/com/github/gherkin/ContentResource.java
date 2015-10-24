package com.github.gherkin;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
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
        Article article = articles.get(id);
        if(article == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }
        return article;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Article insert(Article article) {
        if(article.getTitle() == null && article.getText() == null)
            throw new WebApplicationException("title and boy missing", Status.BAD_REQUEST);

        article.setId(nextId.incrementAndGet());
        articles.put(article.getId(), article);

        return article;
    }
}
