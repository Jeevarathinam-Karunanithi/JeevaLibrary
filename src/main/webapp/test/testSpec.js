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
		
	  });
	  afterEach(function(){
		server.restore();

	  });
	  it("Working with testing",function(){
		server.respondWith("GET", "/some/article/comments.json",
            [200, { "Content-Type": "application/json" },
             '[{ "Book Name": Alchemist, "Author Name": "Paulo","Publisher Name":"Halper coplins","No Of Pages":"122","Date":"29-03-2021" }]']);

	var callback = sinon.spy();
	getbook(callback,"/add");
    server.respond();
	sinon.assert.called(callback);
	//sinon.assert.calledWith(callback, [{ "Book Name": "Alchemist", "Author Name": "Paulo","Publisher Name":"Halper coplins", "No Of Pages":"122","Date":"29-03-2021"}]);

	  });
  });

