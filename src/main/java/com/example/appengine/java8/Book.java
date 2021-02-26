package com.example.appengine.java8;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

@Controller
public class Book {
    private String bookName;
    private String publisherName;
    private String authorName;
    private String noOfPages;

    Book(String bookName,String publisherName,String authorName,String noOfPages){
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.noOfPages = noOfPages;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Object retunrnMapObj(HttpServletRequest request, HttpServletResponse response) {
          Map<Object,String> map = new HashMap();
          map.put("Book Name" , bookName);
          map.put("Author Name", authorName);
          map.put("Publisher Name" , publisherName);
          map.put("No of Pages" , noOfPages);
         
          return map;
         }
}
