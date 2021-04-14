package com.example.appengine.java8;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import javax.servlet.*;  
import javax.servlet.http.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import com.google.appengine.api.datastore.*;

@RunWith(JUnit4.class)
public class DeleteMemcacheTest {

    private final LocalServiceTestHelper helper =
    new LocalServiceTestHelper(new LocalMemcacheServiceTestConfig());

 private DeleteMemcache servletUnderTest;
 @Mock private HttpServletRequest mockRequest;
 @Mock private HttpServletResponse mockResponse;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();
    servletUnderTest = new DeleteMemcache();

  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void getNameTest() throws Exception{
    MemcacheService ms = MemcacheServiceFactory.getMemcacheService();
    Map<String,String> map = new HashMap<String,String>();
    List<Map> lst = new ArrayList<Map>();
    List ls = (List)ms.get("bookList");
    assertNull(ls);
    map.put("Book Name","Alchemist");
    map.put("Author Name","Paulo Coelho");
    map.put("Publisher Name","Halper  Coplins");
    map.put("No Of Pages","121");
    map.put("Date","14-04-2021");
    lst.add(map);
    Map<String,String> map1 = new HashMap<String,String>();
    map1.put("Book Name","Alchemist1");
    map1.put("Author Name","Paulo Coelho1");
    map1.put("Publisher Name","Halper  Coplins1");
    map1.put("No Of Pages","121");
    map1.put("Date","14-04-2021");
    lst.add(map1);
    ms.put("bookList",lst);
    List res = (List)ms.get("bookList");
    Map<String,Object> map2 = new HashMap<String, Object>();
    map2 = (Map)res.get(0);
    assertEquals("Alchemist",map2.get("Book Name"));    
    servletUnderTest.deletedatainmem();
    List result = (List)ms.get("bookList");
    assertNull(result);

  }
}