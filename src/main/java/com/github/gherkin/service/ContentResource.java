package com.github.gherkin.service;

import com.github.gherkin.ChangeLog;
import com.github.gherkin.Content;
import com.github.gherkin.persistence.content.ContentDao;
import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    @Inject
    static Map<String, Content> contentMap;
    @Inject
    static AtomicInteger nextId;
    @Inject
    private ContentDao dao;
    @Inject
    private ChangeLog changeLog;


    @GET
    public Map<String, Content> fetchAll() {
        return dao.retrieveAllMap();
    }

    @GET
    @Path("{id}")
    public Content fetch(@PathParam("id") String id) {
        Content content;
        if(contentMap.containsKey(id)) {
            content = contentMap.get(id);
        } else {
            try {
                content = dao.retrieve(Integer.parseInt(id));
                if(content == null) {
                    throw new WebApplicationException(Status.NOT_FOUND);
                }
                contentMap.put(content.get("id"), content);
            } catch(NumberFormatException exception) {
                throw new WebApplicationException("path parameter is not a number", Status.BAD_REQUEST);
            }
        }

        return content;
    }

    @GET
    @Path("changelog/{id}")
    public Map<String, Content> getChangeLog(@PathParam("id") int id) {
        Map<String, Content> cache = new HashMap<>();
        for (int i = id; i < changeLog.size(); i++) {
            cache.put(changeLog.get(i), contentMap.get(changeLog.get(i)));
        }

        return cache;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Content insert(Content content) {
        if(content == null)
            throw new WebApplicationException("content missing", Status.BAD_REQUEST);

        content.put("id", "" + nextId.incrementAndGet());
        contentMap.put(content.get("id"), content);
        dao.save(content);
        changeLog.add(content.get("id"));

        return content;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Content update(@PathParam("id") String id, Content content) {
        fetch(id);

        contentMap.put(id, content);
        dao.insert(content);
        changeLog.add(content.get("id"));

        return content;
    }

    @DELETE
    @Path("{id}")
    public Content remove(@PathParam("id") String id) {
        fetch(id);

        Content content = contentMap.get(id);
        contentMap.remove(id);
        dao.remove(Integer.parseInt(id));
        changeLog.add(content.get("id"));


        return content;
    }

    public static Map<String, Content> getContentMap() {
        return contentMap;
    }

    public static void setNextId(AtomicInteger nextId) {
        ContentResource.nextId = nextId;
    }

    public static void setContentMap(Map<String, Content> contentMap) {
        ContentResource.contentMap = contentMap;
    }
}
