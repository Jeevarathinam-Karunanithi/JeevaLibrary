package com.example.appengine.java8;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
      out.println(s);
          
    if(userName.equals("admin") && password.equals("admin"))
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