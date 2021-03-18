package com.example.appengine.java8;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
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
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LoginPageTest {
  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Mock private HttpServletRequest mockRequest;
  @Mock private HttpServletResponse mockResponse;
  @Mock private RequestDispatcher rd;
  @Mock private HttpSession session;

  private LoginPage servletUnderTest;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();

    when(mockRequest.getParameter("userName")).thenReturn("admin");
    when(mockRequest.getParameter("userPass")).thenReturn("admin");
    when(mockRequest.getSession()).thenReturn(session);
    when(mockRequest.getRequestDispatcher("/index.jsp")).thenReturn(rd);
    when(mockRequest.getRequestDispatcher("/library.jsp")).thenReturn(rd);

    servletUnderTest = new LoginPage();
  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void logintest() throws Exception {
    servletUnderTest.doPost(mockRequest, mockResponse);
    verify(session).setAttribute("login","li");
    verify(rd).forward(mockRequest, mockResponse);
  }


}