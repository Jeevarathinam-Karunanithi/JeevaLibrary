  
package com.example.appengine.java8;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
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

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import com.google.appengine.api.datastore.*;
@RunWith(JUnit4.class)
public class AddBookTest {

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Mock private HttpServletRequest mockRequest;
  @Mock private HttpServletResponse mockResponse;


  private AddBook servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();
    servletUnderTest = new AddBook();
    
  }
  @After public void tearDown() {
    helper.tearDown();
  }
  private void testDatastore() {
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    Entity book = new Entity("Books");
      book.setProperty("Book Name","Alchemist");
      book.setProperty("Author Name","Paulo cohelo");
      book.setProperty("Publisher Name","Halper Caplins");
      book.setProperty("No of Pages","200");
      book.setProperty("Time","1616032786653");
      book.setProperty("Date","18-03-2021");
      Key k =ds.put(book);
      Query q = new Query("Books");
      PreparedQuery p = ds.prepare(q);
    assertEquals("Alchemist",book.getProperty("Book Name"));
    assertEquals("Paulo cohelo",book.getProperty("Author Name"));
    assertEquals("Halper Caplins",book.getProperty("Publisher Name"));
    assertEquals("200",book.getProperty("No of Pages"));
    assertEquals("1616032786653",book.getProperty("Time"));
    assertEquals("18-03-2021",book.getProperty("Date"));
    assertEquals(1, p.countEntities(withLimit(10)));
    assertEquals(k,book.getKey());
  }

  private void toTestReturnedData() throws Exception{
  String str = "{\"Book Name\":\"Alchemist\",\"Author Name\":\"Paulo cohelo\",\"Publisher Name\":\"Halper Caplins\",\"No of Pages\":200,\"Time\":1616032786653,\"Date\":\"18-03-2021\"}";
  Map<String,Object> obj =  servletUnderTest.addBooktoStore(str);
  assertEquals("Alchemist", obj.get("Book Name"));
  assertEquals("Paulo cohelo", obj.get("Author Name"));
  assertEquals("Halper Caplins", obj.get("Publisher Name"));
  assertEquals(200, obj.get("No of Pages"));
  assertEquals(1616032786653L, obj.get("Time"));
  assertEquals("18-03-2021", obj.get("Date"));
  assertEquals(obj.get("key"),obj.get("key1"));
}

  @Test
  public void addBookTest1()  throws Exception{
    toTestReturnedData();
  }
  @Test
  public void addBookTest2() {
    testDatastore();
  }
}