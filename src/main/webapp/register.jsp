<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<html>
    <head>
        <link rel="stylesheet" href="registerStyle.css">
        <title>Register Page</title>
    </head>
        <body align = "center">
            <% 
            response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate");
            %>
             <div class="main">
            <h3 class="register">Enter User Details </h3>
            <form class="form1" action="/registerpage" method="POST" >
            <input class="nam" type="text" id = "id1"name="name" placeholder="Enter Your Name"><br><br>
            <input class="cit" type = "text" id = "id2" name = "city" placeholder="Enter Your City"><br><br>
            <input class="userNam" type = "text" id = "id3" name = "username" placeholder="Enter Your Username"><br><br>
            <input class="pass" type = "password" id = "id4" name = "password" placeholder="Enter Your Password"><br><br>
            <input class="submit" type="SUBMIT" value="CLICK TO REGISTER">
            <a class="update" align="right" href="uploadPicture.jsp">Upload Picture</a><br><br>
           </form>
        </div>
        </body>

</html> 