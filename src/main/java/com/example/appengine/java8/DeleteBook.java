package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.appengine.api.datastore.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.ErrorHandlers;
import java.util.logging.Level;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import java.io.BufferedReader;
import java.io.IOException; 

import java.util.*;

@Controller
public class DeleteBook extends HttpServlet {

    @RequestMapping(value ="/deletebook",method = RequestMethod.POST)

    public void deleteBook(@RequestBody  String str)
    throws IOException {

      ObjectMapper mapper = new ObjectMapper();
      Map<String,Long> mapNew = mapper.readValue(str, Map.class); 

      Long id = mapNew.get("id");
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Key k = KeyFactory.createKey("Books",id);
      datastore.delete(k);

      Queue queue = QueueFactory.getQueue("delete-queue");
      queue.add(TaskOptions.Builder.withUrl("/deletedata").param("memKey", "bookList"));
   }
}

