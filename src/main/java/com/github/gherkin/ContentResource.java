package com.github.gherkin;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.List;

@Path("content")
public class ContentResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> fetchAll() {
        return Arrays.asList("hello", "world");
    }

}
