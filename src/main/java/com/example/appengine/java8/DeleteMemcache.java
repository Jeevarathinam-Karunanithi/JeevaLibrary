package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.google.appengine.api.datastore.*; 
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.ErrorHandlers;
import java.util.logging.Level;

@Controller
class DeleteMemcache extends HttpServlet{
@RequestMapping(value = "/deletedata", method = RequestMethod.POST)
public void doPost(HttpServletRequest request, HttpServletRequest response){

    
    MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
    memcache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

    String str = request.getParameter("memKey");
    memcache.delete(str);
}
}