package com.example.appengine.java8;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;
import java.util.*;  
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet; 



@Controller
public class FiveUsers extends HttpServlet{
   //@RequestMapping(value = "/fiveusers") 
 //  @WebServlet(name = "FiveUsers", value = "/fiveusers")
   private static final Logger logger = Logger.getLogger(FiveUsers.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException { 

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();  

     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
     Query q = new Query("User");
     PreparedQuery pq = datastore.prepare(q);
     List<Entity> ls = new ArrayList<Entity>();
     ls= pq.asList(FetchOptions.Builder.withLimit(5));
     
     List<Map> userList = new ArrayList<Map>();
     for(Entity ety : ls){ 
        Map<String, Object> map = new HashMap<>(); 
        map.put("USER NAME",ety.getProperty("Name")); 
         userList.add(map);
     }
     try {
        logger.info("Cron Job has been executed");
        logger.info("List" + userList);
       
        }
        catch (Exception ex) {
           System.out.println("ERROR");
        }
    }
     
}
