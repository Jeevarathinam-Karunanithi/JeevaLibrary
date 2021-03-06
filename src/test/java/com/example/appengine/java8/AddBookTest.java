  
package com.example.appengine.java8;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.Reader;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import com.google.appengine.api.datastore.*;
@RunWith(JUnit4.class)
public class AddBookTest {

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Mock private HttpServletRequest mockRequest;
  @Mock private HttpServletResponse mockResponse;
  @Mock private HttpSession session;
  @Mock private BufferedReader reader;


  private AddBook servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();

    when(mockRequest.getSession(false)).thenReturn(session);
    when(session.getAttribute("sessiontAtr")).thenReturn("admin");
    String str = "{\"Book Name\":\"Alchemist\",\"Author Name\":\"Paulo cohelo\",\"Publisher Name\":\"Halper Caplins\",\"No Of Pages\":\"200\",\"Book Number\":\"123456\",\"Status\":\"Available\" ,\"Time\":\"1616032786653\",\"Date\":\"22-03-2021\"}";
    Reader inputString = new StringReader(str);
    BufferedReader reader = new BufferedReader(inputString);
    when(mockRequest.getReader()).thenReturn(reader);
    servletUnderTest = new AddBook();

  }
  @After public void tearDown() {
    helper.tearDown();
  }
  private void toTestReturnedData() throws Exception{
      Map<String,Object> obj =  servletUnderTest.addBooktoStore(mockRequest, mockResponse);
      assertEquals("Alchemist", obj.get("Book Name"));
      assertEquals("Paulo cohelo", obj.get("Author Name"));
      assertEquals("Halper Caplins", obj.get("Publisher Name"));
      assertEquals("200", obj.get("No Of Pages"));
      assertEquals("123456",obj.get("Book Number"));
      assertEquals("Available",obj.get("Status"));
      assertEquals("1616032786653", obj.get("Time"));
      assertEquals("22-03-2021", obj.get("Date"));
      assertEquals(obj.get("key"),obj.get("key1"));
      DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
      Query q = new Query("Books");
      PreparedQuery p = ds.prepare(q);
      Entity result = p.asSingleEntity();
      assertEquals("Alchemist",result.getProperty("Book Name"));
      assertEquals("Paulo cohelo",result.getProperty("Author Name"));
      assertEquals("Halper Caplins",result.getProperty("Publisher Name"));
      assertEquals("200",result.getProperty("No Of Pages"));
      assertEquals("123456",result.getProperty("Book Number"));
      assertEquals("Available",result.getProperty("Status"));
      assertEquals("1616032786653",result.getProperty("Time"));
      assertEquals("22-03-2021",result.getProperty("Date"));
}

  @Test
  public void addBookTest1()  throws Exception{
      toTestReturnedData();
  }
}