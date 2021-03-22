package com.example.appengine.java8;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import javax.servlet.*;  
import javax.servlet.http.*;
import javax.xml.stream.events.EntityDeclaration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.google.appengine.api.datastore.*;
@RunWith(JUnit4.class)
public class GetBookTest {

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Mock private HttpServletRequest mockRequest;
  @Mock private HttpServletResponse mockResponse;

  private GetBook servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();
    servletUnderTest = new GetBook();
  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void getbooktest() throws Exception {
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    Entity book = new Entity("Books");
       book.setProperty("Book Name","Alchemist");
       book.setProperty("Author Name","Paulo cohelo");
       book.setProperty("Publisher Name","Halper Caplins");
       book.setProperty("No Of Pages","200");
       book.setProperty("Time","1616032786653");
       book.setProperty("Date","18-03-2021");
       ds.put(book);
  List<Map> lst = servletUnderTest.getBookFromStore(mockRequest, mockResponse);
    Map<String,Object> m = lst.get(0);
    assertEquals("Alchemist",m.get("Book Name"));
    assertEquals("Paulo cohelo",m.get("Author Name"));
    assertEquals("Halper Caplins",m.get("Publisher Name"));
    assertEquals("200",m.get("No Of Pages"));
    assertEquals("18-03-2021",m.get("Date"));
 
 
}
}