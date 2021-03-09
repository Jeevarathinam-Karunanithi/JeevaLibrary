function login(){
    var name = document.getElementById("userId").value;
    var password = document.getElementById("passId").value;

     var localOb = new Object();
     localOb["name"] = name;
     localOb["password"] = password;
     var js = JSON.stringify(localOb)

     const xht=new XMLHttpRequest();
     xht.open("POST","/loginpage",true);
     xht.setRequestHeader("content-type","application/json");
     xht.send(js);
 
  }