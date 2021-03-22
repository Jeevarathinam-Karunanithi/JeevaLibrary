package com.example.appengine.java8;

import static com.google.common.truth.Truth.assertThat;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

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
  public void Deletetest1() throws Exception {
    String str = "{\"id\":5066549580791809}";
   String s= servletUnderTest.deleteBook(str);
    assertEquals("Success",s);
  }  
}