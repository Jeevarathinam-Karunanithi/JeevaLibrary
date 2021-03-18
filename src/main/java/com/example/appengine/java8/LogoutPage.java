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
       
        response.setContentType("text/html");
        
        HttpSession session=request.getSession(false);
        if(session != null)
        {
        session.removeAttribute("sessiontAtr");   
        session.setAttribute("logout","ls"); 
     
   
        RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
        rd.forward(request, response);
        }
        else{
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
        rd.forward(request, response);
        }
        
        
    }
}