package com.example.appengine.java8;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.appengine.api.datastore.Query.SortDirection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;  

//@WebServlet(name = "GetUser", value = "/getuser")
@Controller
@ResponseBody
public class GetUser extends HttpServlet {
    @Override
    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query("Books").addSort("Time", SortDirection.DESCENDING);
        PreparedQuery pq = datastore.prepare(q);
        List<Entity> lst=new ArrayList<Entity>();
        lst= pq.asList(FetchOptions.Builder.withLimit(5));

        ObjectMapper objMap = new ObjectMapper();
        String str = objMap.writeValueAsString(lst);
       
            response.setContentType("application/json");  
            response.setCharacterEncoding("UTF-8"); 
            response.getWriter().write(str);
    

    }
    @RequestMapping(value = "/returndata", method = RequestMethod.GET)
    public @ResponseBody Object returnData( HttpServletRequest request, HttpServletResponse response){
        Map map=new HashMap();  
        map.put(1,"jeeva");  
        map.put(5,"Rahul");  
        map.put(2,"Jai");  
        map.put(6,"kumar");  
         System.out.println("Map Object returned");
        return map;

    }

        
       

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
    

