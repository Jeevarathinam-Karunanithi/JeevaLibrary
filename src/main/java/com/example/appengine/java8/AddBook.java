package com.example.appengine.java8;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
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

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
//@WebServlet(name = "AddBook", value = "/addbook")
@Controller
public class AddBook extends HttpServlet {
@RequestMapping(value = "/addbook" ,method = RequestMethod.POST)
public @ResponseBody Map<String,Object> addBooktoStore(@RequestBody String js)
    throws IOException {

      MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
      memcache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
      List<Map> ls = new ArrayList<Map>();
      
      
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(js, Map.class); 
      Entity book = new Entity("Books");
      book.setProperty("Book Name",map.get("Book Name"));
      book.setProperty("Author Name",map.get("Author Name"));
      book.setProperty("Publisher Name",map.get("Publisher Name"));
      book.setProperty("No Of Pages",map.get("No Of Pages"));
      book.setProperty("Time",map.get("Time"));
      book.setProperty("Date",map.get("Date"));
      
      DatastoreService d = DatastoreServiceFactory.getDatastoreService();
      Key k = d.put(book);
      Key k1 = book.getKey();
      map.put("key",k);
      map.put("key1", k1);

      Map<String, Object> tempMap = new HashMap<String,Object>();
      tempMap.put("Book Name",map.get("Book Name"));
      tempMap.put("Author Name",map.get("Author Name"));
      tempMap.put("Publisher Name",map.get("Publisher Name"));
      tempMap.put("No Of Pages",map.get("No Of Pages"));
      tempMap.put("Date",map.get("Date"));
      tempMap.put("Key",k);

      
      String json = mapper.writeValueAsString(tempMap);

      Queue queue = QueueFactory.getQueue("add-queue");
      queue.add(TaskOptions.Builder.withUrl("/adddata").param("dataMap", json));
      

     return map; 
    }
   
  }