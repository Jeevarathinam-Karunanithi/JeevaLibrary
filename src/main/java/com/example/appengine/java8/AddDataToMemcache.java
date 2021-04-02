package com.example.appengine.java8;

import java.io.IOException;
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
import javax.servlet.*;  
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.ErrorHandlers;
import java.util.logging.Level;

@Controller
class AddDataToMemcache extends HttpServlet{
@RequestMapping(value = "/adddata", method = RequestMethod.POST)
public void addBooktoMemcache(@RequestBody String jstr)
throws IOException , ServletException{

    
    MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
    memcache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> m = mapper.readValue(jstr, Map.class); 

    List ls = (List)memcache.get("bookList");
    ls.add(0, m);
    int lstIndex = ls.size() - 1;
    ls.remove(lstIndex);
    memcache.put("bookList", ls);
}

}