
package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.SortDirection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.ErrorHandlers;
import java.util.logging.Level;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;  

//@WebServlet(name = "GetUser", value = "/getuser")
@Controller
public class GetBook extends HttpServlet {

    @RequestMapping(value = "/getbook")
    public @ResponseBody List<Map> getBookFromStore(HttpServletRequest request, HttpServletResponse response)throws IOException
   {
       String memKey = "bookList";
       MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
       memcache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

       List<Map> lst2 = new ArrayList<Map>();
       List<Map> ls = new ArrayList<Map>();
       
        ls = (List)memcache.get(memKey);
       // response.getWriter().println("LS" + ls);
        
       if(ls == null || ls.isEmpty())
       {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query("Books").addSort("Time", SortDirection.DESCENDING);
        PreparedQuery pq = datastore.prepare(q);
        List<Entity> lst=new ArrayList<Entity>();
        lst= pq.asList(FetchOptions.Builder.withLimit(5));
        for (Entity e : lst) { 
               Map<String, Object> map = new HashMap<>(); 
                map.put("Book Name",e.getProperty("Book Name")); 
                map.put("Author Name",e.getProperty("Author Name"));
                map.put("Publisher Name",e.getProperty("Publisher Name"));
                map.put("No Of Pages",e.getProperty("No Of Pages"));
                map.put("Date", e.getProperty("Date"));
                map.put("Key",e.getKey());
                lst2.add(map);
            } 
             memcache.put(memKey,lst2);
             return lst2;
         }
          else{
             return ls;
         }
}
}