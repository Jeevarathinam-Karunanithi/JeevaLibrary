<<<<<<< HEAD
package com.example.appengine.java8;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SampleController {
    

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
  public @ResponseBody String sample(
      HttpServletRequest request, HttpServletResponse response) {
          System.out.println("hello");
    return "hello";
      }
}
=======
package com.example.appengine.java8;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SampleController {
    

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
  public @ResponseBody String sample(
      HttpServletRequest request, HttpServletResponse response) {
          System.out.println("hello");
    return "hello";
      }
}
>>>>>>> c882a3fb6e7a53ba14b31704bfbb6f2854a993dc
