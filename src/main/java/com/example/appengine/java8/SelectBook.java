package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import java.io.IOException;
import java.util.*; 

@Controller
public class SelectBook extends HttpServlet{
    @RequestMapping(value = "/selectbook", method = RequestMethod.GET)
    public @ResponseBody List<Map> selctBookFromSore(){
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