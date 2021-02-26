package com.example.appengine.java8;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.apphosting.api.DatastorePb.DatastoreService_3;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

//@WebServlet(name = "AddBook", value = "/addbook")

@Controller
public class AddBook extends HttpServlet {
@RequestMapping(value = "/addbook" ,method = RequestMethod.POST)
public @ResponseBody Object addBooktoStore(@RequestBody String js)
    throws IOException {
      
      ObjectMapper mapper = new ObjectMapper();
      Map<String, String> map = mapper.readValue(js, Map.class); 

      Entity book = new Entity("Books");

      book.setProperty("Book Name",map.get("Book Name"));
      book.setProperty("Author Name",map.get("Author Name"));
      book.setProperty("Publisher Name",map.get("Publisher Name"));
      book.setProperty("No Of Pages",map.get("No of Pages"));
      book.setProperty("Time",map.get("Time"));
      book.setProperty("Date",map.get("Date"));
      
      DatastoreService d = DatastoreServiceFactory.getDatastoreService();

      d.put(book);
      
     return map; 
    }
   
  }





  /* JsonObject jsonobj = JsonObject.fromObject(jb.toString());
    HashMap<String,String> map=new HashMap<String,String>();



    "{
        Book Name\"\": \"Alchemist\",
         \"Author Name\":\"xxx\",
         \"Publisher Name\":\"abc\",
         \"No of Pages\":\"222\"}"; 

    map.put("","");*/