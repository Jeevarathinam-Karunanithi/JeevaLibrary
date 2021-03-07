package com.example.appengine.java8;

import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import java.io.IOException;
import java.util.*; 

@Controller
public class SearchBook extends HttpServlet{
    @RequestMapping(value = "searchbook",method = RequestMethod.POST)
    public  @ResponseBody Object  searchMatchedBook(@RequestBody String str)throws IOException{
         
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> search_map = mapper.readValue(str, Map.class);

        String colHead = search_map.get("columnHeading");
        String searchVal = search_map.get("value");

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Filter matchFilter =
new FilterPredicate(colHead, FilterOperator.EQUAL, searchVal);
        Query qry = new Query("Books").setFilter(matchFilter);
        PreparedQuery preq = datastore.prepare(qry);
        List<Entity> res_lst=new ArrayList<Entity>();
        res_lst= preq.asList(FetchOptions.Builder.withDefaults());
       
        List<Map> resultsList = new ArrayList<Map>();
        for (Entity ety : res_lst) { 
               Map<String, Object> map = new HashMap<>(); 
                map.put("Book Name",ety.getProperty("Book Name")); 
                map.put("Author Name",ety.getProperty("Author Name"));
                map.put("Publisher Name",ety.getProperty("Publisher Name"));
                map.put("No Of Pages",ety.getProperty("No Of Pages"));
                map.put("Date", ety.getProperty("Date"));
                resultsList.add(map);
            } 

            return resultsList;
    }

    
}
