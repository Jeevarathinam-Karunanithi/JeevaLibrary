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

  
          
    if(userName.equals("admin") && password.equals("admin"))
    { 
       // out.println("true");  
       Cookie ck=new Cookie("userName","userCookie");  
       response.setContentType("text/jsp");
       response.addCookie(ck);
            
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