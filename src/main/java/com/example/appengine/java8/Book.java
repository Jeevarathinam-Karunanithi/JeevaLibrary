package com.example.appengine.java8;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import java.util.*;

@Controller
public class Book extends HttpServlet {

    @RequestMapping(value = "/book")
    public @ResponseBody void deleteBook(HttpServletRequest request, HttpServletResponse response)
   {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

   }
}

