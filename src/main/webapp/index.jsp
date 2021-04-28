<!DOCTYPE html>
<!-- [START_EXCLUDE] -->
<%--
  ~ Copyright 2017 Google Inc.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License"); you
  ~ may not use this file except in compliance with the License. You may
  ~ obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
  ~ implied. See the License for the specific language governing
  ~ permissions and limitations under the License.
  --%>
<!-- [END_EXCLUDE] -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<html>
<head>
<link rel="stylesheet" href="indexStyle.css">
<script type="text/javascript" src = "Javascript.js"> </script>
<title>Login Page</title>
<head>
<body align="center">
  <%
  response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate");
  String n=(String)session.getAttribute("sessiontAtr"); 
  String lo=(String)session.getAttribute("logout"); 
  String li=(String)session.getAttribute("login"); 

  if (n != null )
  {
    response.sendRedirect("/library.jsp");
    
  }
  if(li != null)
 {
  out.println("Login With Valid Username and Password");
  session.removeAttribute("login");
  session.invalidate(); 
 }
 if(lo != null){
  out.println("Logged Out Successfully");
  session.removeAttribute("logout");   
  session.invalidate(); 
 }
  %>
  <div class="main">
    <p align="center" class="sign">Sign in</p>

  <input class="un" align="center" type="text" id = "n1" name="userName" placeholder="Username"><br><br>
  <input class="pass" align="center" type="password" id = "n2" name="userPass" placeholder="Password"><br><br>  
  <!-- <input class="submit" type="submit" align="center" value="LOGIN">   -->
  <button class="submit" align = "center" onclick="login()">LOGIN</button>
  
  <h3 align="center" class="user"> New User </h3>
 <button class="register" align="center" onclick="location.href='/register.jsp'" type="button">
    REGISTER</button>
  </div>

<body>
</html>