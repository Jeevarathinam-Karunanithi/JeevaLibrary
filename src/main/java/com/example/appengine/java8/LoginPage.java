package com.example.appengine.java8;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.PreparedQuery;
import javax.servlet.annotation.WebServlet;  
import java.io.*;
import javax.servlet.*;  
import javax.servlet.http.*;
import java.util.*;  

@WebServlet(name = "LoginPage", value = "/loginpage")
public class LoginPage extends HttpServlet{
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
   
      PrintWriter out = response.getWriter();  
          
      String userName=request.getParameter("userName");  
      String password =request.getParameter("userPass"); 

      Date date = new Date();

      long timeMilli = date.getTime();
      String s=String.valueOf(timeMilli);
     // out.println(s);
     // out.println("name :  " + userName);
      
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Filter flt1 = new FilterPredicate("Username" , FilterOperator.EQUAL, userName);
      Filter flt2 = new FilterPredicate("Password" , FilterOperator.EQUAL, password);
      CompositeFilter UserFilter = CompositeFilterOperator.and(flt1,flt2);
      Query q = new Query("User").setFilter(flt1);
      PreparedQuery pq = datastore.prepare(q);
      Entity result = pq.asSingleEntity();
      if(result == null)
      {
        RequestDispatcher rd=request.getRequestDispatcher("/register.jsp"); 
        rd.forward(request, response);
      }
    //  out.println("\n result " + result);
      String user = result.getProperty("Username").toString();
      String pass = result.getProperty("Password").toString();
    //  out.println("\n user  :" + u + "    " + "pass : "+ p);

    if(user != null && pass != null)
    { 
       // out.println("true");  
        HttpSession session=request.getSession();  
        session.setAttribute("sessiontAtr",s);  
       // response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");    
        RequestDispatcher rd=request.getRequestDispatcher("/library.jsp"); 
        rd.forward(request, response);
        
    } 
        else{  
            out.println("Username or Password is Invalid");  
           
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
           rd.forward(request, response);
    } 
}

}