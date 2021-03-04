package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.appengine.api.datastore.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@Controller
public class DeleteBook extends HttpServlet {

    @RequestMapping(value = "/deletebook", method = RequestMethod.POST)
    public void deleteBook(@RequestBody Object obj){
    //public void deleteBook(HttpServletRequest request, HttpServletResponse response)throws IOException {
      Key k = (Key)obj;
       DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      datastore.delete(k);
   }


   /*StringBuffer jb = new StringBuffer();
   String json = "";
   BufferedReader reader = request.getReader();
   while ((json = reader.readLine()) != null)
       jb.append(json);
   String js = jb.toString();*/
 
}

