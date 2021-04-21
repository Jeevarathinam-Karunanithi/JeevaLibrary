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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.google.appengine.api.datastore.*;

@RunWith(JUnit4.class)
public class ReturnBookTest {

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  private ReturnBook servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();
    servletUnderTest = new ReturnBook();
  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void returnBookTest() throws Exception {
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
   Entity book = new Entity("Books",6758376219876267L);
      book.setProperty("Book Number","123456");
      book.setProperty("Book Name","Alchemist");
      book.setProperty("Author Name","Paulo cohelo");
      book.setProperty("Publisher Name","Halper Caplins");
      book.setProperty("No Of Pages","200");
      book.setProperty("Date","21-03-2021");
      book.setProperty("Status","Unavailable");
      ds.put(book);
      Query q = new Query("Books");
      PreparedQuery p = ds.prepare(q);
      Entity result = p.asSingleEntity();
      assertNotNull(result);
      assertEquals("Unavailable",result.getProperty("Status"));
      String str = "{\"Book Number\":\"123456\"}";
      servletUnderTest.returnBookToStore(str);
      Query qry = new Query("Books");  
      PreparedQuery pr = ds.prepare(q);
      Entity result1 = pr.asSingleEntity();
      assertEquals("Available",result1.getProperty("Status"));
       
}
} 