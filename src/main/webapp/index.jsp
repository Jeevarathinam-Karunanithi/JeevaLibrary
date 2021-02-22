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

<script type="text/javascript" src = "Javascript.js"> </script>
<style>
  table, th, td {
    border: 1px solid black;
  }
  </style>
       
</head>
<body onload="getuser()">
           
           <h2>JEEVA LIBRARY</h2>
            
             <div id = "test">
              
              <label for = "n1">Book Name:</label><br>
              <input type = "text" id = "n1" name = "v1"/><br><br>
              <label for = "n1">Author Name:</label><br>
              <input type = "text" id = "n2" name = "v2"/><br><br>
              <label for = "n1">Publisher Name:</label><br>
              <input type = "text" id = "n3" name = "v3"/><br><br>
              <label for = "n1">No of Pages:</label><br>
              <input type = "text" id = "n4" name = "v4"/><br><br>
              <button onclick= "loadDoc()">SUBMIT</button><br><br> 
            
              </div>
 
              <table>
                <h1>Recently Added Books</h1>
                <tr>
                  <th> BOOK NAME </th>
                   <th> AUTHOR NAME </th>
                     <th> PUBLISHER NAME</th>
                       <th> NO OF PAGES </th>
                       <th> DATE </th>
                </tr>
                         
               <tbody  id = "tableones">
                
                 </tbody>

              </table><br><br><br>

<!-- SAMPLE TABLE                   
              <table>
               <tr>
                 <th> BOOK NAME </th>
                  <th> AUTHOR NAME </th>
                    <th> PUBLISHER NAME</th>
                      <th> NO OF PAGES </th>
                       
                    
                </tr>
                        
                 <tbody id = "table">
                      <button onclick="sample()">SAMPLE TABLE</button><br><br>
                </tbody>
                    
              </table> !-->
                 

                </body>
</html>
