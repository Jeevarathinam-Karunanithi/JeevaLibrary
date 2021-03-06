package com.example.appengine.java8;

import static com.google.common.truth.Truth.assertThat;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import com.google.appengine.api.datastore.*;
import java.util.*;

@RunWith(JUnit4.class)
public class DeleteBookTest {
  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

      private DeleteBook servletUnderTest;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();
    servletUnderTest = new DeleteBook();
  }

  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void Deletetest2() throws Exception {
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    Entity book = new Entity("Books",6758376219876267L);
      book.setProperty("Book Name","Alchemist");
      book.setProperty("Author Name","Paulo cohelo");
      book.setProperty("Publisher Name","Halper Caplins");
      book.setProperty("No of Pages","200");
      book.setProperty("Time","1616032786653");
      book.setProperty("Date","18-03-2021");
      Key key = ds.put(book);
      
      Query q = new Query("Books");
      PreparedQuery p = ds.prepare(q);
      Entity result = p.asSingleEntity();
      assertNotNull(result);
      String str = "{\"id\":6758376219876267}";
      servletUnderTest.deleteBook(str);
      Query qry = new Query("Books");  
      PreparedQuery pr = ds.prepare(q);
      Entity result1 = pr.asSingleEntity();
      assertEquals(result1,null); 
  }
}