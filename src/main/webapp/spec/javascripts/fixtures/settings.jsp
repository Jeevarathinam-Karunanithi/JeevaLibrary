<!DOCTYPE html>
<html lang="en">
<head>
    <script type="text/javascript" src = "Javascript.js"> </script>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<% 
response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate");
String str=(String)session.getAttribute("sessiontAtr"); 
if(str == null)
{
  RequestDispatcher rd=request.getRequestDispatcher("/index.jsp"); 
  rd.forward(request, response);
  
}

%>
<body onload="getNamefromStore()">
    <div id="inputDiv">
        <label for="inputid">Edit You Name :</label>&nbsp;
        <input type="text" id="inputid" name="inputName"/>
        <button onclick="updateName()">CONFIRM CHANGES</button>

    </div>
</body>
</html>