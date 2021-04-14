  
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
public class FiveUsersTest {

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

 @Mock private HttpServletRequest mockRequest;
 @Mock private HttpServletResponse mockResponse;

 private FiveUsers servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();
    servletUnderTest = new FiveUsers();

  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void getUsersTest() throws Exception{
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Entity ety1 = new Entity("User");
      ety1.setProperty("Name","jeeva1");
      ety1.setProperty("City","Karur");
      ety1.setProperty("Username","admin"); 
      ety1.setProperty("Password","admin"); 
      datastore.put(ety1);
      Entity ety2 = new Entity("User");
      ety2.setProperty("Name","jeeva2");
      ety2.setProperty("City","Karur");
      ety2.setProperty("Username","admin"); 
      ety2.setProperty("Password","admin"); 
      datastore.put(ety2);
      Entity ety3 = new Entity("User");
      ety3.setProperty("Name","jeeva3");
      ety3.setProperty("City","Karur");
      ety3.setProperty("Username","admin"); 
      ety3.setProperty("Password","admin"); 
      datastore.put(ety3);
      Entity ety4 = new Entity("User");
      ety4.setProperty("Name","jeeva4");
      ety4.setProperty("City","Karur");
      ety4.setProperty("Username","admin"); 
      ety4.setProperty("Password","admin"); 
      datastore.put(ety4);
      Entity ety5 = new Entity("User");
      ety5.setProperty("Name","jeeva5");
      ety5.setProperty("City","Karur");
      ety5.setProperty("Username","admin"); 
      ety5.setProperty("Password","admin"); 
      datastore.put(ety5);

      List<Map> lst = servletUnderTest.getname(mockRequest, mockResponse);
      assertEquals(5,lst.size());
  }
}
