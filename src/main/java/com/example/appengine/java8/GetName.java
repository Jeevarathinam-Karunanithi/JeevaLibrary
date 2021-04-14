package com.example.appengine.java8;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;  

@Controller
class GetName extends HttpServlet{
    @RequestMapping(value = "/getname")
                
    public @ResponseBody Map<String, Object> getNamefromStore(HttpServletRequest request, HttpServletResponse response)throws IOException{

          HttpSession session=request.getSession(false);
          String name = (String)session.getAttribute("sessiontAtr");

          DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
          Filter nameFilter = new FilterPredicate("Username",FilterOperator.EQUAL,name);
          Query q = new Query("User").setFilter(nameFilter);
          PreparedQuery p = datastore.prepare(q);
          Entity result = p.asSingleEntity();

          Map<String , Object> m = new HashMap<String , Object>();
          m.put("name",result.getProperty("Name"));
          m.put("nameKey",result.getKey());
          return m;
    }

}