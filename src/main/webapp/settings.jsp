<!DOCTYPE html>
<html lang="en">
<head>
    
    <script type="text/javascript" src = "Javascript.js"> </script>
    <link rel="stylesheet" href="settingsStye.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Name</title>
</head>
<% 
String str=(String)session.getAttribute("sessiontAtr"); 
if(str == null)
{
  RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
  rd.forward(request, response);
  
}
%>
<body onload="getNamefromStore()">
    <%
    response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate");
    %>
    <div id="inputDiv" class="main">
        <p class="change" align="center">Edit Your Name</p>
        <input align="center" class="input" type="text" id="inputid" name="inputName"><br><br>
        <button class="update" onclick="updateName()">CONFIRM CHANGES</button><br><br>
        <button class="back" align="center" onclick="location.href='/library.jsp'" type="button">Back</button>
    </div>
</body>
</html>