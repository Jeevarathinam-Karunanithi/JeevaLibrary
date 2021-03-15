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
import org.springframework.security.crypto.bcrypt.BCrypt;

@WebServlet(name = "LoginPage", value = "/loginpage")
public class LoginPage extends HttpServlet{
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
       String user = null;
       String pass = null;
       Entity result = null;

       response.setContentType("text/html");
       PrintWriter out = response.getWriter();  
       String userName=request.getParameter("userName");  
       String passWord =request.getParameter("userPass"); 
       Date date = new Date();
       long timeMilli = date.getTime();
       String s=String.valueOf(timeMilli);

//   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
//    String result12 = encoder.encode("myPassword");
//    String result123 = encoder.encode("myPassword");
// String r1 = BCrypt.hashpw("123", BCrypt.gensalt(10));
//String r2 = BCrypt.hashpw("123", BCrypt.gensalt(10));

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Filter flt1 = new FilterPredicate("Username" , FilterOperator.EQUAL, userName);
      Query q = new Query("User").setFilter(flt1);
      PreparedQuery pq = datastore.prepare(q);
      result = pq.asSingleEntity();
      if(result != null )
      {
         user = result.getProperty("Username").toString();
         pass = result.getProperty("Password").toString();
         if(BCrypt.checkpw(passWord, pass)){
            HttpSession session=request.getSession();  
            session.setAttribute("sessiontAtr",s);  
            RequestDispatcher rd=request.getRequestDispatcher("/library.jsp"); 
            rd.forward(request, response);
         }
          else{
         //   out.println("Enter a Vallid Password or Password index");
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
            rd.forward(request, response); 
         }
   }
   else{ 
        // out.println("Enter a Vallid Username or Password index");
         RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
         rd.forward(request, response);     
       
  }
 }   
}