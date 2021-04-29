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
import org.json.JSONObject;

@WebServlet(name = "LoginPage", value = "/loginpage")
public class LoginPage extends HttpServlet{
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
     // response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate");
       String user = null;
       String pass = null;
       String name = null;
       Entity result = null;
 
       HttpSession session = request.getSession();
       session.setAttribute("login","li");

       response.setContentType("text/html");
       PrintWriter out = response.getWriter();  
       StringBuffer jb = new StringBuffer();
       String jsonStr = "";
       BufferedReader reader = request.getReader();   
       while ((jsonStr = reader.readLine()) != null)
          jb.append(jsonStr);
       String js = jb.toString();
        JSONObject jsobj = new JSONObject(js);
        String userName =  jsobj.getString("name");
        String passWord =  jsobj.getString("password");
      
        JSONObject jsob = new JSONObject();
        jsob.put("Status","failed");

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Filter flt1 = new FilterPredicate("Username" , FilterOperator.EQUAL, userName);
      Query q = new Query("User").setFilter(flt1);
      PreparedQuery pq = datastore.prepare(q);
      result = pq.asSingleEntity();
      if(result != null )
      {
         user = result.getProperty("Username").toString();
         pass = result.getProperty("Password").toString();
         name = result.getProperty("Name").toString();
         if(BCrypt.checkpw(passWord, pass)){

            session.removeAttribute("login");
            session.setAttribute("sessiontAtr",userName);
            session.setAttribute("name",name); 
            // request.setAttribute("Name",userName);
            out.println(jsobj);
            // RequestDispatcher rd=request.getRequestDispatcher("/library.jsp"); 
            // rd.forward(request, response);
         }
          else{
            out.println(jsob);
         //   out.println("Enter a Vallid Password or Password index");
            // RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
            // rd.forward(request, response); 
         }
   }
   else{ 
           out.println(jsob);
        // out.println("Enter a Vallid Username or Password index");
         // RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
         // rd.forward(request, response);     
       
  }
 }   
}
