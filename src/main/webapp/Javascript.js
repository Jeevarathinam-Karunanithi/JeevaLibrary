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
         "No of Pages": noOfPages,
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

      const arr = new Array();
       const xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
         var ob = JSON.parse(xhttp.responseText);
         for(var i= 0; i < ob.length; i++)
           {
             globalObj[i] = ob[i]["Key"];
            // console.log("obj",globalObj);
              var row = `<tr>       
                     <td>${ob[i]["Book Name"]}</td>  
                     <td>${ob[i]["Publisher Name"]}</td>
                     <td>${ob[i]["Author Name"]}</td>
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
    var temp = globalObj[i-1];
     var js = JSON.stringify(temp);
   //  console.log(js);
     document.getElementById("tableID").deleteRow(i);
     
      const xh = new XMLHttpRequest();
      xh.open('POST','/deletebook',true);
      xh.setRequestHeader("Content-Type", "application/json");
      xh.send(js); 
    

}