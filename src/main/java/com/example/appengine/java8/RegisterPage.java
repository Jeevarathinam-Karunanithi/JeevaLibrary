package com.example.appengine.java8;

import java.io.IOException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;  
import java.io.*;
import javax.servlet.*;  
import javax.servlet.http.*;
import java.util.*;  

//@controller

@WebServlet(name = "RegisterPage", value = "/registerpage")
public class RegisterPage extends HttpServlet{
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
   
      PrintWriter out = response.getWriter();  
          
      String name=request.getParameter("name");  
      String city =request.getParameter("city"); 
      String username=request.getParameter("username");  
      String password =request.getParameter("password"); 

      DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
      Entity e = new Entity("User");
      e.setProperty("Name",name);
      e.setProperty("City",city);
      e.setProperty("Username",username); 
      e.setProperty("Password",password); 

      ds.put(e);
      response.sendRedirect("/index.jsp");
  
    }
}