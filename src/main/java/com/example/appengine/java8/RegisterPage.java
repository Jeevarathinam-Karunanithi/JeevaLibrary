package com.example.appengine.java8;

import java.io.IOException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import javax.servlet.annotation.WebServlet;  
import java.io.*;
import javax.servlet.*;  
import javax.servlet.http.*;
import java.util.*; 
import org.springframework.security.crypto.bcrypt.BCrypt; 

//@controller

@WebServlet(name = "RegisterPage", value = "/registerpage")
public class RegisterPage extends HttpServlet{
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      DatastoreService ds = DatastoreServiceFactory.getDatastoreService();  
      
      
      String name=request.getParameter("name");  
      String city =request.getParameter("city"); 
      String username=request.getParameter("username");  
      String password =request.getParameter("password"); 
      String pass = BCrypt.hashpw(password, BCrypt.gensalt(10));
      
      Filter f = new FilterPredicate("Username",FilterOperator.EQUAL,username);
      Query q = new Query("User").setFilter(f);
      PreparedQuery p = ds.prepare(q);
      Entity result = p.asSingleEntity();
      if(result != null)
      {
          RequestDispatcher rd=request.getRequestDispatcher("/register.jsp"); 
          rd.include(request, response); 
    
      }
      else{
      Entity e = new Entity("User");
      e.setProperty("Name",name);
      e.setProperty("City",city);
      e.setProperty("Username",username); 
      e.setProperty("Password",pass); 

       ds.put(e);
      RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
      rd.forward(request, response); 
      }
      
    }
}