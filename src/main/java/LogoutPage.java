package com.example.appengine.java8;


import org.springframework.stereotype.Controller;
import javax.servlet.annotation.WebServlet;  
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;
import java.util.*;  


@WebServlet(name = "LogoutPage", value = "/logoutpage")
public class LogoutPage extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException { 
        response.setContentType("text/jsp");  
        PrintWriter out=response.getWriter();  
          
          
        Cookie ck=new Cookie("userName","");  
        ck.setMaxAge(0);  
        response.addCookie(ck);  

        out.println("you are successfully logged out!");  
        RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);  

    }
}