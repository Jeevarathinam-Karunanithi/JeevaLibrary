var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; 
var yyyy = today.getFullYear();
if(dd<10) 
{
    dd='0'+dd;
} 
if(mm<10) 
{
    mm='0'+mm;
} 
today = dd+'-'+mm+'-'+yyyy;

var nameObj = new Object();

function login(){
    var username = document.getElementById("n1").value;
    var password = document.getElementById("n2").value;
     var localOb = {
         "name" : username,
         "password" : password 
     };
    
     var js = JSON.stringify(localOb)
     const xht = new XMLHttpRequest();
     xht.onreadystatechange = function() {
        if (this.readyState == 4) {
            console.log(JSON.parse(this.responseText));
            var data = JSON.parse(this.responseText);
            console.log(data["Status"]);
            if(data["Status"] == "failed"){
                window.location = "/index.jsp"
            }
            else{
            document.getElementById("n1").value = "";
            document.getElementById("n2").value = "";
            window.location = '/library.jsp';
            }
        }
  }
  xht.open("POST","/loginpage",true);
  xht.setRequestHeader("content-type","application/json");
  xht.send(js);
}
function loadDoc(){
    var bookName = document.getElementById("n1").value;
    var authorName = document.getElementById("n2").value;
    var publisherName = document.getElementById("n3").value;
    var noOfPages = document.getElementById("n4").value;
    var date = new Date();
    var time = date.getTime();
    var bookNumber = time + "";
    var obj = {
      "Book Name": bookName,
      "Author Name": authorName,
      "Publisher Name": publisherName,
      "No Of Pages": noOfPages,
      "Book Number" : bookNumber,
      "Time" : Date.now(),
      "Date" : today
    
  };
    document.getElementById("n1").value = "";
    document.getElementById("n2").value = "";
    document.getElementById("n3").value = "";
    document.getElementById("n4").value = "";     
    const jstr = JSON.stringify(obj);
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status ==200){
             var data = JSON.parse(this.responseText);
            //  if(data["Name"] != "admin" ){
            //     var p = document.createElement("p");
            //     p.id = "paraId";
            //     p.innerHTML = "";
            //     p.innerHTML = "You don't have permission to Add book";
            //     p.style.color = "red";
            //     let div = document.getElementById("divError");
            //     div.appendChild(p);
            // }  
        }
    }
    xhttp.open('POST','/addbook',true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(jstr);
}
   
var globalObj = new Object();

function getbook() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    var res = xhttp.responseText;
    var ob = JSON.parse(res);
    console.log("Ob",ob);
     var imageUrl = ob[ob.length-1]["imageUrl"]
      var imgId = document.getElementById("img");
         var image = document.createElement("img");
         image.src = imageUrl;
         image.style = "float:right";
         image.width = "70";
         image.height = "70";
         image.alt="Profile Picture";
         var imgdiv = document.getElementById("imgdiv");
         imgdiv.appendChild(image);
        
    for(var i= 0; i < ob.length-1; i++){
        globalObj[i] = ob[i]["Key"]["id"];
        var name = ob[ob.length-1]["name"];
        var imageUrl = ob[ob.length-1]["imageUrl"]
        console.log("Imageurl", imageUrl);
        
        console.log(name);
         if(name != "admin"){
            var div = document.getElementById("addbook")
            div.style.display = "none";
            }

        var row = `<tr>       
        <td>${ob[i]["Book Name"]}</td>  
        <td>${ob[i]["Author Name"]}</td>
        <td>${ob[i]["Publisher Name"]}</td>
        <td>${ob[i]["No Of Pages"]}</td>
        <td>${ob[i]["Date"]}</td>
        <td><button style="color:white;background-color:#964537" type=button value=Delete onclick=deleteRow(this)>DELETE</button></td>
        </tr>`
        document.getElementById("tableones").innerHTML += row;
       }
    }  
  }
    xhttp.open('GET','/getbook',true);
    xhttp.send();
}

function deleteRow(r) {
    var i = r.parentNode.parentNode.rowIndex;
    var localObj = new Object();
    localObj["id"] = globalObj[i-1];
    var js = JSON.stringify(localObj);
    const xh = new XMLHttpRequest();
    xh.onreadystatechange = function() {
        if (this.readyState == 4 || this.status == 200) {
            document.getElementById("tableID").deleteRow(i);
        }
    }
    xh.open('POST','/deletebook',true);
    xh.setRequestHeader("Content-Type", "application/json");
    xh.send(js); 
}

function searchBook(){
    var title = document.getElementById("d1").value;
    var name = document.getElementById("input").value;
    var lo_obj = new Object();
    lo_obj["columnHeading"] = title;
    lo_obj["value"] = name;
    var temp = JSON.stringify(lo_obj);
    const xht = new XMLHttpRequest();
    xht.onreadystatechange = function() {
    if(xht.readyState == 4 && this.status ==200){
        var data = JSON.parse(xht.responseText);
        //console.log("SEARCH",data);
        var table = document.getElementById("tableBodySearch");
        table.innerHTML="";
        for(var i= 0; i < data.length; i++){
            var row = `<tr>       
            <td>${data[i]["Book Name"]}</td>  
            <td>${data[i]["Author Name"]}</td>
            <td>${data[i]["Publisher Name"]}</td>
            <td>${data[i]["No Of Pages"]}</td>
            <td>${data[i]["Date"]}</td>
            </tr>`
            document.getElementById("tableBodySearch").innerHTML += row;
      }
    }
  }
    xht.open('POST','/searchbook',true)
    xht.setRequestHeader("Content-Type" , "application/json");
    xht.send(temp)
}

var globalNameObj = new Object();

function getNamefromStore(){
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
    if(xhr.readyState == 4 && this.status ==200){
        var data = JSON.parse(xhr.responseText);
        console.log("data",data);
        var name1 = data["name"];
        console.log(name1);
        globalNameObj["id"] = data["nameKey"]["id"];
        console.log("ID",globalNameObj["id"]);
        document.getElementById("inputid").value = name1;
    }
  }
    xhr.open("GET","/getname",true);
    xhr.send();
}

//globalNameObj["id"] = 121;
function updateName(){
    var nameStr = document.getElementById("inputid").value;
    var longid = globalNameObj["id"];
    var idToString = longid + "";
    var localNameObj = new Object();
    localNameObj["name"] = nameStr;
    localNameObj["id"] = idToString;
    var js = JSON.stringify(localNameObj); 
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
    if(xhr.readyState == 4 && this.status == 200){
       console.log("Completed");
    }
   // window.location = '/library.jsp';
  }
    xhr.open("POST","/addname",true);
    xhr.setRequestHeader("Content-Type" , "application/json");
    xhr.send(js);
}
var globalSelectobj = {};
function selectbook(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var data = JSON.parse(xhr.responseText);
            if(data.length != 0){
            var table = document.createElement("table");
            table.id = "tableSelect";
            var divtag = document.getElementById("divSelect");
            divtag.appendChild(table);
            var thead = document.getElementById("tableSelect");
            thead.innerHTML="";
            var temp = `<tr>
                        <th>BOOK NUMBER</th>
                        <th>BOOK NAME</th>
                        <th>AUTHOR NAME</th>
                        <th>PUBLISHER NAME</th>
                        <th>NO OF PAGES</th>
                        <th>STATUS</th>
                        <th>SELECT</th>
                        </tr>`
            thead.innerHTML += temp;
            // var bk = document.getElementById("tableSelect").rows[0].cells[0].innerHTML;
            for(let i = 0; i < data.length; i++){
                globalSelectobj[i] = data[i]["Key"]["id"];
                var temp1 = `<tr>
                            <td>${data[i]["Book Number"]}</td>
                            <td>${data[i]["Book Name"]}</td>
                            <td>${data[i]["Author Name"]}</td>
                            <td>${data[i]["Publisher Name"]}</td>
                            <td>${data[i]["No Of Pages"]}</td>
                            <td>${data[i]["Status"]}</td>
                            <td><button style="color:white;background-color:#964537" type=button value=Borrow onclick=selectrow(this)>BORROW</button></td>
                            </tr>`
            thead.innerHTML +=temp1;
            }
      }
    }
    
 }
    xhr.open('GET','/selectbook',true);
    xhr.send();
}
function selectrow(r){
    var i = r.parentNode.parentNode.rowIndex;
    var tempObj = {};
    tempObj["id"] = globalSelectobj[i-1]; 
    var jsonString = JSON.stringify(tempObj);
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if( this.readyState == 4 && this.status == 200){
           console.log("Updated");
        }
    }
    xhr.open("POST","/updatestatus",true);
    xhr.setRequestHeader("Content-Type" , "application/json");
    xhr.send(jsonString);
}
function returnBook(){
    var bookNum = document.getElementById("bknumber").value;
    var bookNumObj = {};
    bookNumObj["Book Number"] = bookNum;
    var jsonString = JSON.stringify(bookNumObj);
    document.getElementById("bknumber").value = "";
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            console.log("Updated");
        }

    } 
    xhr.open("POST","/returnbook",true);
    xhr.setRequestHeader("Content-Type" , "application/json");
    xhr.send(jsonString);
}
function deleteUser(){
    var localObj = {};
    localObj["id"] = "121";
    var js = JSON.stringify(localObj);
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            console.log("Updated");
        }
    } 
    xhr.open("POST","/deleteuser",true);
    xhr.send(js);

}