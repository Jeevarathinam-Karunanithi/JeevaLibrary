package com.example.appengine.java8;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.apphosting.api.DatastorePb.DatastoreService_3;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.AdminDatastoreService.EntityBuilder;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.FetchOptions.Builder;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.appengine.api.datastore.Query.SortDirection;
import java.sql.Timestamp;
import java.util.*;  
import java.io.IOException;


@WebServlet(name = "GetUser", value = "/getuser")
public class GetUser extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        List<Entity> lst=new ArrayList<Entity>();

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         
        Query q = new Query("Books").addSort("Time", SortDirection.DESCENDING);

        PreparedQuery pq = datastore.prepare(q);
        lst= pq.asList(FetchOptions.Builder.withLimit(5));
        ObjectMapper objMap = new ObjectMapper();
        String str = objMap.writeValueAsString(lst);
       
            response.setContentType("application/json");  
            response.setCharacterEncoding("UTF-8"); 
            response.getWriter().write(str);
    

        
        
       

       /* List<Entity> lst=new ArrayList<Entity>(); 
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query q = new Query("Books");

        PreparedQuery pq = datastore.prepare(q);
        lst = pq.asList(FetchOptions.Builder.withLimit(5)); 
        PrintWriter out = response.getWriter();
        out.println(lst);*/
        //response.getWriter().println(pq.asList(FetchOptions.Builder.withLimit(5)));
       // request.setAttribute("lst", lst);
       // response.setContentType("text/html");
       // request.getRequestDispatcher("index.jsp").forward(request, response);



       /* StringBuffer jb = new StringBuffer();
    
        String json = "";
        BufferedReader reader = request.getReader();   
        while ((json = reader.readLine()) != null)
           jb.append(json);

        ObjectMapper objMap = new ObjectMapper();
        String str = objMap.writeValueAsString(jb);
        
        response.setContentType("application/json");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(str); 
        
         for(Entity en:pq.asIterable())
        {
            String s = en.getProperty("Book Name").toString();
            response.getWriter().println(s);
        } 



         lst= pq.asList(FetchOptions.Builder.withLimit(5));
        ObjectMapper objMap = new ObjectMapper();
        String str = objMap.writeValueAsString(lst);
        
        response.setContentType("application/json");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(str);


        */

} 
}
    

