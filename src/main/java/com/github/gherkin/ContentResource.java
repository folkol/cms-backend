package com.github.gherkin;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Path("content")
public class ContentResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HashMap<String, String>> fetchAll() {
        HashMap<String, String> content1 = createArticle("0", "Hej", "n[got smart");
        HashMap<String, String> content2 = createArticle("0", "Eluceite", "n[got dumt");

        return Arrays.asList(content1, content2);
    }

    private HashMap<String, String> createArticle(String value, String hej, String value1) {
        HashMap<String, String> content = new HashMap<>();
        content.put("id", value);
        content.put("title", hej);
        content.put("text", value1);
        return content;
    }

}
