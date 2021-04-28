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
  @Mock private HttpSession session;

  private GetBook servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();

    when(mockRequest.getSession(false)).thenReturn(session);
    when(session.getAttribute("sessiontAtr")).thenReturn("admin");
    
    servletUnderTest = new GetBook();
  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void getbooktest() throws Exception {
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    Date date = new Date();
    long timeMilli = date.getTime();
    Entity book = new Entity("Books");
    book.setProperty("Book Name","Alchemist");
    book.setProperty("Author Name","Paulo cohelo");
    book.setProperty("Publisher Name","Halper Caplins");
    book.setProperty("No Of Pages","200");
    book.setProperty("Time",1);
    book.setProperty("Date","23-03-2021");
    ds.put(book);
    Entity book1 = new Entity("Books");
       book1.setProperty("Book Name","Alchemist1");
       book1.setProperty("Author Name","Paulo cohelo");
       book1.setProperty("Publisher Name","Halper Caplins");
       book1.setProperty("No Of Pages","200");
       book1.setProperty("Time",2);
       book1.setProperty("Date","23-03-2021");
       ds.put(book1);
       Entity book2 = new Entity("Books");
       book2.setProperty("Book Name","Alchemist2");
       book2.setProperty("Author Name","Paulo cohelo");
       book2.setProperty("Publisher Name","Halper Caplins");
       book2.setProperty("No Of Pages","200");
       book2.setProperty("Time",3);
       book2.setProperty("Date","23-03-2021");
       ds.put(book2);
       Entity book3 = new Entity("Books");
       book3.setProperty("Book Name","Alchemist3");
       book3.setProperty("Author Name","Paulo cohelo");
       book3.setProperty("Publisher Name","Halper Caplins");
       book3.setProperty("No Of Pages","200");
       book3.setProperty("Time",4);
       book3.setProperty("Date","23-03-2021");
       ds.put(book3);
       Entity book4 = new Entity("Books");
       book4.setProperty("Book Name","Alchemist4");
       book4.setProperty("Author Name","Paulo cohelo");
       book4.setProperty("Publisher Name","Halper Caplins");
       book4.setProperty("No Of Pages","200");
       book4.setProperty("Time",5);
       book4.setProperty("Date","23-03-2021");
       ds.put(book4);
       Entity book5 = new Entity("Books");
       book5.setProperty("Book Name","Alchemist5");
       book5.setProperty("Author Name","Paulo cohelo");
       book5.setProperty("Publisher Name","Halper Caplins");
       book5.setProperty("No Of Pages","200");
       book5.setProperty("Time",6);
       book5.setProperty("Date","23-03-2021");
       ds.put(book5);
  List<Map> lst = servletUnderTest.getBookFromStore(mockRequest, mockResponse);
   assertEquals(6,lst.size());
   Map<String,Object> m1 = lst.get(0);
    assertEquals("Alchemist5",m1.get("Book Name"));
    Map<String,Object> m2 = lst.get(1);
    assertEquals("Alchemist4",m2.get("Book Name"));
    Map<String,Object> m3 = lst.get(2);
    assertEquals("Alchemist3",m3.get("Book Name"));
    Map<String,Object> m4 = lst.get(3);
    assertEquals("Alchemist2",m4.get("Book Name"));
    Map<String,Object> m5 = lst.get(4);
    assertEquals("Alchemist1",m5.get("Book Name"));
    
 }
}