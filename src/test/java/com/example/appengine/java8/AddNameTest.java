  
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
public class AddNameTest {

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
 private AddName servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();
    servletUnderTest = new AddName();

  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void addNameTest() throws Exception{
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Entity ety = new Entity("User",6758376219876267L);
      ety.setProperty("Name","jeeva");
      ety.setProperty("City","Karur");
      ety.setProperty("Username","admin"); 
      ety.setProperty("Password","admin"); 
      datastore.put(ety);

      Query q = new Query("User");
      PreparedQuery pq = datastore.prepare(q);
      Entity e = pq.asSingleEntity();
      assertNotNull(e);
      assertEquals("jeeva",ety.getProperty("Name"));
      String Str = "{\"name\":\"JeevaChanged\",\"id\":\"6758376219876267\"}";
      servletUnderTest.addnameToStore(Str);
      Query qt = new Query("User");
      PreparedQuery pqt = datastore.prepare(q);
      Entity result = pq.asSingleEntity();
      assertEquals("JeevaChanged",result.getProperty("Name"));
  }
}
