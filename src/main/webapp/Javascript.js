
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



  function getbook() {

     
      const xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
         var ob = JSON.parse(xhttp.responseText);

         //console.log(ob)
        
          var tableone = document.getElementById("tableones")
         

        // console.log(ob[0]["properties"]["Book Name"]);
        //Not able to access the data in object
        for(var i= 0; i < ob.length; i++)
        {
          var row = `<tr>       
                     <td>${ob[i]["Book Name"]}</td>  
                     <td>${ob[i]["Publisher Name"]}</td>
                     <td>${ob[i]["Author Name"]}</td>
                     <td>${ob[i]["No Of Pages"]}</td>
                     <td>${ob[i]["Date"]}</td>

                     </tr>`
                     tableone.innerHTML += row;
        } 
       
    }
};

      xhttp.open('GET','/getbook',true);
      xhttp.send();
}


function getmap(){

   const xhMap = new XMLHttpRequest();
    xhMap.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
       var obmap =  JSON.parse(xhMap.responseText);
       //console.log(obmap);
    }
  }
  xhMap.open('GET','/returndata',true);
  xhMap.send();
}

//Sample 

 /* function sample(){
    var obj = [{
        "Book": "The New Day",
         "Author":"Alex",
         "Publisher":"Brain",
         "Pages":"200"
    },
    {
        "Book": "The New Day1",
         "Author":"Alex",
         "Publisher":"Brain",
         "Pages":"200"
    },
    {
        "Book": "The New Day3",
         "Author":"Alex",
         "Publisher":"Brain",
         "Pages":"200"
    },
]
     
     
         
         var table = document.getElementById("table")
         for(var i = 0; i < obj.length ; i++){
         var row = `<tr>
                    <td>${obj[i].Book}</td>
                    <td>${obj[i].Author}</td>
                    <td>${obj[i].Publisher}</td>
                    <td>${obj[i].Pages}</td>
                    </tr>`
                    table.innerHTML += row
         }
       /*
  myObj = JSON.parse(this.responseText);
          txt += "<table border='1'>"
          for (x in myObj) {
            txt += "<tr><td>" + myObj[x]["Book Name"] + "</td></tr>";
          }
          txt += "</table>" */
  