package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import javax.servlet.*; 
import com.google.appengine.api.datastore.*; 
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.ErrorHandlers;
import java.util.logging.Level;
import java.io.IOException;

@RestController
class DeleteMemcache extends HttpServlet{
@RequestMapping(value = "/deletedata")
public void deletedatainmem()
throws IOException , ServletException{

    try{
    MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
    memcache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
    memcache.delete("bookList");
}
catch(Exception e){
    System.out.println("Error");
}
    
}
}