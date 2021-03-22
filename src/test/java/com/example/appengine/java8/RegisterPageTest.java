package com.example.appengine.java8;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;
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

import jdk.nashorn.internal.objects.annotations.Where;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class RegisterPageTest {
  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Mock private HttpServletRequest mockRequest;
  @Mock private HttpServletResponse mockResponse;
  @Mock private RequestDispatcher rd;
  @Mock private PreparedQuery p;

  private RegisterPage servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();

    when(mockRequest.getParameter("name")).thenReturn("jeeva");
    when(mockRequest.getParameter("city")).thenReturn("chennai");
    when(mockRequest.getParameter("username")).thenReturn("admin");
    when(mockRequest.getParameter("password")).thenReturn("admin");
    when(mockRequest.getRequestDispatcher("/index.jsp")).thenReturn(rd);
    when(mockRequest.getRequestDispatcher("/register.jsp")).thenReturn(rd);

    servletUnderTest = new RegisterPage();
  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void registerTest1() throws Exception {
    servletUnderTest.doPost(mockRequest, mockResponse);
    verify(rd).forward(mockRequest, mockResponse);
  }

}