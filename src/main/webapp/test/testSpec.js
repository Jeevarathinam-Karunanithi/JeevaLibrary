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


describe("A suite to test getbook" ,function(){
	var server;
    beforeEach(function(){
		 loadFixtures('library.jsp');
		 server = sinon.fakeServer.create();
	});
	afterEach(function(){
		server.restore();

	});
    it("which adds data to the table",function(){
		server.respondWith("GET", "/getbook",
            [200, { "Content-Type": "application/json" },
            '[{ "Key":{"id":"121"},"Book Name": "Alchemist1", "Author Name": "Paulo1","Publisher Name":"Halper coplins1","No Of Pages":"121","Date":"29-03-2021" },{ "Key":{"id":"122"},"Book Name": "Alchemist2", "Author Name": "Paulo2","Publisher Name":"Halper coplins2","No Of Pages":"122","Date":"29-03-2021" },{ "Key":{"id":"123"},"Book Name": "Alchemist3", "Author Name": "Paulo3","Publisher Name":"Halper coplins3","No Of Pages":"123","Date":"29-03-2021" },{ "Key":{"id":"124"},"Book Name": "Alchemist4", "Author Name": "Paulo4","Publisher Name":"Halper coplins4","No Of Pages":"124","Date":"29-03-2021" },{ "Key":{"id":"125"},"Book Name": "Alchemist5", "Author Name": "Paulo5","Publisher Name":"Halper coplins5","No Of Pages":"125","Date":"29-03-2021" }]']);

	getbook();
    server.respond();

    var bookName1 = document.getElementById("tableones").rows[0].cells[0].innerHTML;
	var authorName1 = document.getElementById("tableones").rows[0].cells[1].innerHTML;
	var publisherName1 = document.getElementById("tableones").rows[0].cells[2].innerHTML;
	var noOfPages1 =  document.getElementById("tableones").rows[0].cells[3].innerHTML;
	var date1 =  document.getElementById("tableones").rows[0].cells[4].innerHTML;

	var bookName2 = document.getElementById("tableones").rows[1].cells[0].innerHTML;
	var authorName2 = document.getElementById("tableones").rows[1].cells[1].innerHTML;
	var publisherName2 = document.getElementById("tableones").rows[1].cells[2].innerHTML;
	var noOfPages2 =  document.getElementById("tableones").rows[1].cells[3].innerHTML;
	var date2 =  document.getElementById("tableones").rows[1].cells[4].innerHTML;

	var bookName3 = document.getElementById("tableones").rows[2].cells[0].innerHTML;
	var authorName3 = document.getElementById("tableones").rows[2].cells[1].innerHTML;
	var publisherName3 = document.getElementById("tableones").rows[2].cells[2].innerHTML;
	var noOfPages3 =  document.getElementById("tableones").rows[2].cells[3].innerHTML;
	var date3 =  document.getElementById("tableones").rows[2].cells[4].innerHTML;

	var bookName4 = document.getElementById("tableones").rows[3].cells[0].innerHTML;
	var authorName4 = document.getElementById("tableones").rows[3].cells[1].innerHTML;
	var publisherName4 = document.getElementById("tableones").rows[3].cells[2].innerHTML;
	var noOfPages4 =  document.getElementById("tableones").rows[3].cells[3].innerHTML;
	var date4 =  document.getElementById("tableones").rows[3].cells[4].innerHTML;

	var bookName5 = document.getElementById("tableones").rows[4].cells[0].innerHTML;
	var authorName5 = document.getElementById("tableones").rows[4].cells[1].innerHTML;
	var publisherName5 = document.getElementById("tableones").rows[4].cells[2].innerHTML;
	var noOfPages5 =  document.getElementById("tableones").rows[4].cells[3].innerHTML;
	var date5 =  document.getElementById("tableones").rows[4].cells[4].innerHTML;

	expect(bookName1).toBe("Alchemist1");
	expect(authorName1).toBe("Paulo1");
	expect(publisherName1).toBe("Halper coplins1");
	expect(noOfPages1).toBe("121");
	expect(date1).toBe("29-03-2021");

	expect(bookName2).toBe("Alchemist2");
	expect(authorName2).toBe("Paulo2");
	expect(publisherName2).toBe("Halper coplins2");
	expect(noOfPages2).toBe("122");
	expect(date2).toBe("29-03-2021");

	expect(bookName3).toBe("Alchemist3");
	expect(authorName3).toBe("Paulo3");
	expect(publisherName3).toBe("Halper coplins3");
	expect(noOfPages3).toBe("123");
	expect(date3).toBe("29-03-2021");

	expect(bookName4).toBe("Alchemist4");
	expect(authorName4).toBe("Paulo4");
	expect(publisherName4).toBe("Halper coplins4");
	expect(noOfPages4).toBe("124");
	expect(date4).toBe("29-03-2021");

	expect(bookName5).toBe("Alchemist5");
	expect(authorName5).toBe("Paulo5");
	expect(publisherName5).toBe("Halper coplins5");
	expect(noOfPages5).toBe("125");
	expect(date5).toBe("29-03-2021");
      });
  });
  describe("A suite to test to test searchbook",function(){
   var server;
   beforeEach(function(){
    server = sinon.fakeServer.create();
	loadFixtures('library.jsp');
	  });
	  afterEach(function(){
		server.restore();

	  });
	  it("which returns the searched data from the server",function(){
   
	      document.getElementById("d1").value = "Book Name";
		  document.getElementById("input").value = "Alchemist";
		  var localObj = {
			"columnHeading" : "Book Name",
			 "value" : "Alchemist"
		  };
        var jsonLocalObj = JSON.stringify(localObj);
		server.respondWith("POST", '/searchbook',
            [200, { "Content-Type": "application/json" },
             '[{ "Key":{"id":"121"},"Book Name": "Alchemist1", "Author Name": "Paulo1","Publisher Name":"Halper coplins1","No Of Pages":"121","Date":"29-03-2021" },{ "Key":{"id":"122"},"Book Name": "Alchemist1", "Author Name": "Paulo2","Publisher Name":"Halper coplins2","No Of Pages":"122","Date":"29-03-2021" },{ "Key":{"id":"123"},"Book Name": "Alchemist1", "Author Name": "Paulo3","Publisher Name":"Halper coplins3","No Of Pages":"123","Date":"29-03-2021" }]']);

	searchBook();
    server.respond();
	var resBdy = server.requests[1].requestBody;
    expect(jsonLocalObj).toEqual(resBdy);

	var bookName1 = document.getElementById("tableBodySearch").rows[0].cells[0].innerHTML;
	var bookName2 = document.getElementById("tableBodySearch").rows[1].cells[0].innerHTML;
	var bookName3 = document.getElementById("tableBodySearch").rows[2].cells[0].innerHTML;

	expect(bookName1).toBe("Alchemist1");
	expect(bookName2).toBe("Alchemist1");
	expect(bookName3).toBe("Alchemist1");
   });

  });

  describe("A suite to test to test addbook",function(){
	var server;
	beforeEach(function(){
	 server = sinon.fakeServer.create();
	 loadFixtures('library.jsp');
	   });
	   afterEach(function(){
		 server.restore();

	   });
	   it("which adds data to the server",function(){
		  document.getElementById("n1").value="Alchemist";
		  document.getElementById("n2").value="Paulo";
		  document.getElementById("n3").value="Halper";
		  document.getElementById("n4").value="122";
		  document.getElementById("n5").value="123456";
      
		 var local_obj = {
			"Book Name": "Alchemist",
			"Author Name": "Paulo",
			"Publisher Name":"Halper",
			"No Of Pages":"122",
			"Book Number" :"123456",
			"Time" : Date.now(),
			"Date": today
		 }
	 var jsonLocalObj = JSON.stringify(local_obj);
	 loadDoc();
	 server.respondWith("POST",'/addbook',
	 [200, { "Content-Type": "application/json" },
    '[{ "Key":{"id":"121"},"Book Name": "Alchemist1", "Author Name": "Paulo1","Publisher Name":"Halper coplins1","No Of Pages":"121","Book Number":"123456","Date":"29-03-2021"  }]']);
     
    var responseBdy = server.requests[1].requestBody;
    console.log(server.requests[1].requestBody);
	server.respond();
	expect(jsonLocalObj).toEqual(responseBdy);


	});

   });

   describe("A suite to test deletebook",function(){
	var server;
	beforeEach(function(){
	 server = sinon.fakeServer.create();
	   });
	   afterEach(function(){
		 server.restore();

	   });
	   it("which delets row from the server",function(){
		  var r = {
			parentNode : {
				parentNode : {
					rowIndex : 1
				}
			}
		  };
		  var sampleobj = {
			  "id" : "121"
		  }
		  var jsonSampleobj = JSON.stringify(sampleobj);
		  deleteRow(r);
		   var localobj= server.requests[0].requestBody;
		  expect(jsonSampleobj).toEqual(localobj);

	});

   });

describe("A suite to test GetName", function(){
	var server;
	beforeEach(function(){
		loadFixtures('settings.jsp');
	   server = sinon.fakeServer.create();
	   });
	   afterEach(function(){
		 server.restore();

	   });
	   it("Which get the name form the datastore", function(){

	    server.respondWith("GET","/getname",
 		[200, { "Content-Type": "application/json" },
		'{ "nameKey":{"id":"121"},"name": "jeeva" }']);

		getNamefromStore();
		server.respond();
 	   var name = document.getElementById("inputid").value;
 	   console.log(name);
 	   expect(name).toBe("jeeva");
		
	 });

   });

   describe("A suite to test AddName", function(){
	var server;
	beforeEach(function(){
		loadFixtures('settings.jsp');
	   server = sinon.fakeServer.create();
	   });
	   afterEach(function(){
		 server.restore();

	   });
	   it("Which update the name form the datastore", function(){
		document.getElementById("inputid").value = "jeeva";
		var local_obj = {
			name: 'jeeva',
			id: '121'
		}
		var local_JSON = JSON.stringify(local_obj);
		updateName();
		var responseBdy = server.requests[0].requestBody;
		server.respond();
		expect(local_JSON).toEqual(responseBdy);
		
	 });

   });


   describe("A suite to test selectbook" ,function(){
	var server;
    beforeEach(function(){
		 server = sinon.fakeServer.create();
		 loadFixtures('library.jsp');
	});
	afterEach(function(){
		server.restore();

	});
    it("which select a row from the table",function(){
		server.respondWith("GET", "/selectbook",
            [200, { "Content-Type": "application/json" },
            '[{ "Key":{"id":"121"},"Book Number" : "123456", "Book Name": "Alchemist1", "Author Name": "Paulo1","Publisher Name":"Halper coplins1","No Of Pages":"121"},{ "Key":{"id":"122"},"Book Number" : "123457","Book Name": "Alchemist2", "Author Name": "Paulo2","Publisher Name":"Halper coplins2","No Of Pages":"122"},{ "Key":{"id":"123"},"Book Number" : "123458","Book Name": "Alchemist3", "Author Name": "Paulo3","Publisher Name":"Halper coplins3","No Of Pages":"123" }]']);

	selectbook();
    server.respond();
    
	var bookNum1 = document.getElementById("tableSelect").rows[1].cells[0].innerHTML;
    var bookName1 = document.getElementById("tableSelect").rows[1].cells[1].innerHTML;
	var authorName1 = document.getElementById("tableSelect").rows[1].cells[2].innerHTML;
	var publisherName1 = document.getElementById("tableSelect").rows[1].cells[3].innerHTML;
	var noOfPages1 =  document.getElementById("tableSelect").rows[1].cells[4].innerHTML;

	var bookNum2 = document.getElementById("tableSelect").rows[2].cells[0].innerHTML;
	var bookName2 = document.getElementById("tableSelect").rows[2].cells[1].innerHTML;
	var authorName2 = document.getElementById("tableSelect").rows[2].cells[2].innerHTML;
	var publisherName2 = document.getElementById("tableSelect").rows[2].cells[3].innerHTML;
	var noOfPages2 =  document.getElementById("tableSelect").rows[2].cells[4].innerHTML;

	var bookNum3 = document.getElementById("tableSelect").rows[3].cells[0].innerHTML;
	var bookName3 = document.getElementById("tableSelect").rows[3].cells[1].innerHTML;
	var authorName3 = document.getElementById("tableSelect").rows[3].cells[2].innerHTML;
	var publisherName3 = document.getElementById("tableSelect").rows[3].cells[3].innerHTML;
	var noOfPages3 =  document.getElementById("tableSelect").rows[3].cells[4].innerHTML;

	expect(bookNum1).toBe("123456");
	expect(bookName1).toBe("Alchemist1");
	expect(authorName1).toBe("Paulo1");
	expect(publisherName1).toBe("Halper coplins1");
	expect(noOfPages1).toBe("121");

	expect(bookNum2).toBe("123457");
	expect(bookName2).toBe("Alchemist2");
	expect(authorName2).toBe("Paulo2");
	expect(publisherName2).toBe("Halper coplins2");
	expect(noOfPages2).toBe("122");

    expect(bookNum3).toBe("123458");
	expect(bookName3).toBe("Alchemist3");
	expect(authorName3).toBe("Paulo3");
	expect(publisherName3).toBe("Halper coplins3");
	expect(noOfPages3).toBe("123");
      });
  });
  describe("A suite to test selectrow",function(){
	var server;
	beforeEach(function(){
	 server = sinon.fakeServer.create();
	   });
	   afterEach(function(){
		 server.restore();

	   });
	   it("which select row and update in the server",function(){
		  var r = {
			parentNode : {
				parentNode : {
					rowIndex : 1
				}
			}
		  };
		  var sampleobj = {
			  "id" : "121"
		  }
		  var jsonSampleobj = JSON.stringify(sampleobj);
		  selectrow(r);
		   console.log(server.requests);
		   var localobj= server.requests[0].requestBody;
		  expect(jsonSampleobj).toEqual(localobj);

	});

});
  describe("A suite to test returnBook",function(){
	var server;
	beforeEach(function(){
		server = sinon.fakeServer.create();
		loadFixtures('library.jsp');
	});
	afterEach(function(){
		server.restore();
    });
	it("which update in the server when the book is returned",function(){
		document.getElementById("bknumber").value = "121";
		var sampleobj = {
			"Book Number" : "121"
		}
		var jsonSampleobj = JSON.stringify(sampleobj);
		returnBook();
		var localobj= server.requests[1].requestBody;
		expect(jsonSampleobj).toEqual(localobj);
    });
});
