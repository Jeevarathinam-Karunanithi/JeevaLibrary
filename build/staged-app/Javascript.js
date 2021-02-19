
var obj = {
  "Book Name": "The New Day 1",
   "Author Name":"Alex ",
   "Publisher Name":"Brain",
   "No of Pages":200,
   "Time" :Date.now()
};
function loadDoc(){
  
        const jstr = JSON.stringify(obj)
        const xhttp = new XMLHttpRequest();

        xhttp.open('POST','/addbook',true);
        xhttp.setRequestHeader("Content-Type", "application/json");
        xhttp.send(jstr);
    }



  function getuser() {

     
      const xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
         var ob = JSON.parse(xhttp.responseText);
       //  console.log(JSON.stringify(ob))
        
          var tableone = document.getElementById("tableones")
         

        // console.log(ob[0]["properties"]["Book Name"]);
        //Not able to access the data in object
        for(var i= 0; i < ob.length; i++)
        {
          var row = `<tr>       
                     <td>${ob[i]["properties"]["Book Name"]}</td>  
                     <td>${ob[i]["properties"]["Publisher Name"]}</td>
                     <td>${ob[i]["properties"]["Author Name"]}</td>
                     <td>${ob[i]["properties"]["No Of Pages"]}</td>
                     <td>${ob[i]["properties"]["Time"]}</td>

                     </tr>`
                     tableone.innerHTML += row;
        } 
       
    }
};

      xhttp.open('GET','/getuser',true);
      xhttp.send();

  
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
  