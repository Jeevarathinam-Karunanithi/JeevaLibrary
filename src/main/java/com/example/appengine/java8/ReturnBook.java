package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException; 
import java.util.*;
import java.lang.*; 
import javax.servlet.*; 

@Controller
@RequestMapping(value ="/returnbook")
public class ReturnBook extends HttpServlet {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void returnBookToStore(@RequestBody  String js)
    throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> mapNew = mapper.readValue(js, Map.class); 
        String bknumsStr = mapNew.get("Book Number");
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Filter flt = new FilterPredicate("Book Number", FilterOperator.EQUAL, bknumsStr);
        Query q = new Query("Books").setFilter(flt);
        PreparedQuery pq = datastore.prepare(q);
        Entity et = pq.asSingleEntity();
        Key k = et.getKey();
        Long id  = k.getId();

        try{
            Entity userEntity = datastore.get(KeyFactory.createKey("Books", id));
            userEntity.setProperty("Status","Available");
            datastore.put(userEntity);
            }
        catch(EntityNotFoundException e) {
                
        }
   }
}

