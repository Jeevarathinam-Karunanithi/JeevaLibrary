<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<html>
    <head>
    </head>
        <body align = "center">
            <% 
            response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate");
            %>
            
            <h3>Enter User Details </h3>
            <form action="/registerpage" method="POST" >
            <label for = "id1">NAME :</label>&nbsp;
            <input type="text" id = "id1"name="name" /><br><br>
            <label for = "id2"> CITY :</label>&nbsp;
            <input type = "text" id = "id2" name = "city"/><br><br>
            <label for = "id3"> USERNAME :</label>&nbsp;
            <input type = "text" id = "id2" name = "username"/><br><br>
            <label for = "id3"> PASSWORD :</label>
            <input type = "password" id = "id2" name = "password"/><br><br>
            <input type="SUBMIT" value="REGISTER">&nbsp;
           </form>
            
        </body>

</html>