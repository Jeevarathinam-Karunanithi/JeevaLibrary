package com.example.appengine.java8;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
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
public class GetNameTest {

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
 private GetName servletUnderTest;
 @Mock private HttpServletRequest mockRequest;
 @Mock private HttpServletResponse mockResponse;
 @Mock private HttpSession session;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();

    when(mockRequest.getSession(false)).thenReturn(session);
    when(session.getAttribute("sessiontAtr")).thenReturn("admin");
    servletUnderTest = new GetName();

  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void getNameTest() throws Exception{
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Entity ety = new Entity("User");
    ety.setProperty("Name","jeeva");
    ety.setProperty("City","Karur");
    ety.setProperty("Username","admin"); 
    ety.setProperty("Password","admin"); 
    datastore.put(ety);
    Map<String,Object> m = servletUnderTest.getNamefromStore(mockRequest,mockResponse);
    assertEquals("jeeva",m.get("name"));
      
  }
}