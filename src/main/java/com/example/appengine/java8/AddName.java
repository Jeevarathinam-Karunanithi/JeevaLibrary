package com.example.appengine.java8;

import com.google.appengine.api.datastore.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import javax.servlet.http.*;
import java.io.*;  
import java.util.*;
import java.util.*;
import javax.servlet.*;
import java.lang.*; 
@Controller
@RequestMapping(value = "/addname")
public class AddName extends HttpServlet {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void addnameToStore(@RequestBody  String js)throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> map = mapper.readValue(js, Map.class);
        
        String str = map.get("name");
        String idstr = map.get("id");
        Long id = Long.parseLong(idstr);
        try{
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity userEntity = datastore.get(KeyFactory.createKey("User", id));
        userEntity.setProperty("Name", str);
        datastore.put(userEntity);
        }
        catch(EntityNotFoundException e) {
            
            }
      
    }
}