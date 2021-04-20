<!DOCTYPE html>
<!-- [START_EXCLUDE] <a href="logoutpage">Logout</a>|-->
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
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<html>
<head>
<script type="text/javascript" src = "Javascript.js"> </script>
<link rel="stylesheet" href="libraryStyle.css">
<style>
  table, th, td {
    border: 1px solid black;
  }
  </style>
       
</head>
<% 
response.setHeader("Cache-Control", "no-cache , no-store");
String str=(String)session.getAttribute("sessiontAtr"); 
if(str == null)
{
  RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
  rd.forward(request, response);
  
}

%>
<body onload="getbook()">
 <% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); %>
            <h2 class="title">JEEVA LIBRARY</h2>
            <a class="settings" align="right" href="settings.jsp">SETTINGS</a><br><br>
            <button class="logout" align="right" onclick="location.href='/logoutpage'" type="button">LOGOUT</button>
            
            <div id = "test">
              <p class="addbook">Add BooksTo Library</p>
              <input class="book" type = "text" id = "n1" name = "v1" placeholder="Enter Book Name"><br>
              <input class="author" type = "text" id = "n2" name = "v2" placeholder="Enter Author Name"><br>
              <input class="publisher" type = "text" id = "n3" name = "v3" placeholder="Enter Publisher Name"><br>
              <input class="page" type = "text" id = "n4" name = "v4" placeholder="No of Pages"><br>
              <input class="number" type = "text" id= "n5" name="v5" placeholder="Enter Book Number" maxlength="6"><br>
              <button class="submit" onclick= "loadDoc()">SUBMIT</button><br><br> 
            </div>
         
            <p class="searchHeading">Search Books From Library</p>
            <select class="dropDown" name = "heading" id = "d1">
                 <option class="drop" value="" disabled selected hidden>Select Heading</option>
                 <option class="drop" value = "Book Name">BOOK NAME</option> 
                 <option class="drop" value = "Author Name">AUTHOR NAME</option> 
                 <option class="drop" value = "Publisher Name">PUBLISHER NAME</option> 
            </select><br><br>
            <input class="searchText" type = "text" id = "input" name = "input1" placeholder="Enter the text to search"> <br>
            <button class="search" onclick= "searchBook()">SEARCH</button><br><br>
  
            <table id = "tableSearch">
            <p class="searchResults">Your Search Results</p>
            <tr class="head1">
              <th id="boook"> BOOK NAME </th>
              <th id="author"> AUTHOR NAME </th>
              <th id="publiser"> PUBLISHER NAME</th>
              <th id="pages"> NO OF PAGES </th>
              <th id="date"> DATE </th>
           </tr>
           <tbody  id="tableBodySearch" class="tablebody1">
           </tbody>
           </table><br><br><br>
 
           <table id = "tableID">
           <p class="recent">Recently Added Books</p>
           <tr class="head2">
              <th id="book2"> BOOK NAME </th>
              <th id="author2"> AUTHOR NAME </th>
              <th id="publisher2"> PUBLISHER NAME</th>
              <th id="pages2"> NO OF PAGES </th>
              <th id="date2"> DATE </th>
              <th id="delete2"> DELETE </th>
            </tr>
            <tbody  id = "tableones" class="tablebody2">
            </tbody>

            </table>
             
   </body>
</html>
