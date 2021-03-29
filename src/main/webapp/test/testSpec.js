describe("Sample test module1", function() {

	beforeEach(function() {

		loadFixtures('register.jsp');
		
	});
	
	afterEach(function() {
        
	});
	
		
	it("sample test1", function() {

	});

});

describe("Sample test module2", function() {

	beforeEach(function() {

		loadFixtures('library.jsp');
		
	});
	
	afterEach(function() {
        
	});
	
		
	it("sample test2", function() {
		
	});

});
describe("sample test",function(){
	var a = true;
	it("testing the boolean value",function(){
        expect(a).toBe(true);
	});
});

describe("A sample test", function(){
     it("can be used for later",function(){
		 expect(sample(false)).toBe(true);
	 });
});

  var server;
  describe("Testing the server" ,function(){
      beforeEach(function(){
		 server = sinon.fakeServer.create();
		 loadFixtures('library.jsp');
	  });
	  afterEach(function(){
		server.restore();

	  });
	  it("Working with testing",function(){
		server.respondWith("GET", "/getbook",
            [200, { "Content-Type": "application/json" },
             '[{ "Key":{"id":"123"},"Book Name": "Alchemist", "Author Name": "Paulo","Publisher Name":"Halper coplins","No Of Pages":"122","Date":"29-03-2021" }]']);

	getbook();
    server.respond();
	
	//sinon.assert.called(callback);
	//sinon.assert.calledWith(callback, [{ "Book Name": "Alchemist", "Author Name": "Paulo","Publisher Name":"Halper coplins", "No Of Pages":"122","Date":"29-03-2021"}]);

	  });
  });

