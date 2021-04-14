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
function loadDoc(){
       var bookName = document.getElementById("n1").value;
       var authorName = document.getElementById("n2").value;
       var publisherName = document.getElementById("n3").value;
       var noOfPages = document.getElementById("n4").value;
       var obj = {
        "Book Name": bookName,
         "Author Name": authorName,
         "Publisher Name": publisherName,
         "No Of Pages": noOfPages,
         "Time" : Date.now(),
         "Date" : today
      };
           
       const jstr = JSON.stringify(obj)
      
        const xhttp = new XMLHttpRequest();
        
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
         for(var i= 0; i < ob.length; i++)
           {
              globalObj[i] = ob[i]["Key"]["id"];
            // console.log("obj",globalObj);
              var row = `<tr>       
                     <td>${ob[i]["Book Name"]}</td>  
                     <td>${ob[i]["Author Name"]}</td>
                     <td>${ob[i]["Publisher Name"]}</td>
                     <td>${ob[i]["No Of Pages"]}</td>
                     <td>${ob[i]["Date"]}</td>
                     <td><button type=button value=Delete onclick=deleteRow(this)>DELETE</button></td>
                     </tr>`
                     document.getElementById("tableones").innerHTML += row;
            }
           // console.log(globalObj);
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
  console.log("title",title);
  var temp = JSON.stringify(lo_obj);
  const xht = new XMLHttpRequest();
  xht.onreadystatechange = function() {
  if(xht.readyState == 4 && this.status ==200){
    var data = JSON.parse(xht.responseText);
    console.log("SEARCH",data);
    var table = document.getElementById("tableBodySearch");
    table.innerHTML="";
    for(var i= 0; i < data.length; i++)
    {
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
    document.getElementById("inputid").value = "";

  }
  xhr.open("POST","/addname",true);
  xhr.setRequestHeader("Content-Type" , "application/json");
  xhr.send(js);
}


