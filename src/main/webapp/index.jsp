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
<%@ page import="com.example.appengine.java8.HelloAppEngine" %>
<%@ page import="com.example.appengine.java8.AddBook" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>

<html>
<head>
    <script type="text/javascript" src = "loginjavascript.js"></script>
<head>
<body align="center">
<form action="loginpage" method="post">  
  <label for ="n1">USERNAME :</label> &nbsp;
  <input type="text" id = "n1" name="userName"/><br><br>
  <label for ="n1">PASSWORD :</label> &nbsp;
  <input type="password" id = "n2" name="userPass"/><br><br>  
  <input type="submit" value="login"/>  
  </form> 
<!--<table align = "centre">
<tr>
<td>USERNAME</td>
<td><input type ="text" id = "userId"name="username"></td>
</tr>
<tr>
<td>PASSWORD</td>
<td><input type ="password" id = "passId" name="pass"></td>
</tr>
<td><button onclick="login()">SUBMIT</button>
</table> !-->
<body>
</html>