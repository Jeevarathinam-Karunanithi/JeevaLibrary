package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.appengine.api.datastore.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import java.io.BufferedReader;
import java.io.IOException; 
import java.util.*;
import java.lang.Long;
import javax.servlet.*; 

@Controller
@RequestMapping(value ="/updatestatus")
public class UpdateStatus extends HttpServlet {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> updateBookStatus(@RequestBody  String js)
    throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Long> mapNew = mapper.readValue(js, Map.class); 
        Long id = mapNew.get("id");
        try{
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            Entity userEntity = datastore.get(KeyFactory.createKey("Books", id));
            userEntity.setProperty("Status","Unavailable");
            datastore.put(userEntity);
            }
        catch(EntityNotFoundException e) {
                
        }
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Filter fltr = new FilterPredicate("Status", FilterOperator.EQUAL, "Available");
        Query q = new Query("Books").setFilter(fltr);
        PreparedQuery pq = datastore.prepare(q);
        List<Entity> lst = pq.asList(FetchOptions.Builder.withDefaults());
        List<Map> result = new ArrayList<Map>();
        for (Entity ety : lst) { 
            Map<String, Object> map = new HashMap<>(); 
            map.put("Book Number",ety.getProperty("Book Number")); 
            map.put("Book Name",ety.getProperty("Book Name")); 
            map.put("Author Name",ety.getProperty("Author Name"));
            map.put("Publisher Name",ety.getProperty("Publisher Name"));
            map.put("No Of Pages",ety.getProperty("No Of Pages"));
            map.put("Status", ety.getProperty("Status"));
            map.put("Key",ety.getKey());
            result.add(map);
        } 
        return result;
   }
}

