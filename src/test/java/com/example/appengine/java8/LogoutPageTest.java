package com.example.appengine.java8;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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
public class LogoutPageTest {
  private final LocalServiceTestHelper helper = new LocalServiceTestHelper();

  @Mock private HttpServletRequest mockRequest;
  @Mock private HttpServletResponse mockResponse;
  @Mock private RequestDispatcher rd;
  @Mock private HttpSession session;
  
  private StringWriter responseWriter;
  private LogoutPage servletUnderTest;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();

    when(mockRequest.getRequestDispatcher("/index.jsp")).thenReturn(rd);
    when(mockRequest.getSession(false)).thenReturn(session);

    servletUnderTest = new LogoutPage();
  }
  @After public void tearDown() {
    helper.tearDown();
  }
  @Test
  public void logoutTest() throws Exception {
    servletUnderTest.doGet(mockRequest, mockResponse);
    verify(session).removeAttribute("sessiontAtr");
    verify(session).setAttribute("logout","ls");
    verify(rd).forward(mockRequest, mockResponse);
  }
}