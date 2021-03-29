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

describe("Test for getbook",function(){
	beforeEach(function(){
		jasmine.Ajax.install();
	});
	afterEach(function() {
		jasmine.Ajax.uninstall();
	  });
	it("With ajax call", function() {
		var doneFn = jasmine.createSpy("spyon");
		var xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function(args) {
        if (this.readyState == this.DONE) {
          doneFn(this.responseText);
        }
      };
	  xhr.open("GET",'/getbook');
      xhr.send();
	  expect(jasmine.Ajax.requests.mostRecent().url).toBe('/getbook');
      expect(doneFn).not.toHaveBeenCalled();
});
});

// describe("functions",function(){

// var xhr, requests;

// beforeEach(function () {
//   xhr = useFakeXMLHttpRequest();
//   requests = [];
//   xhr.onCreate = function (req) {
//     requests.push(req);
//   };
// });

// afterEach(function () {
//   xhr.restore();
// });

// it("makes a GET request for todo items", function () {
//   getbook();

//   assert.equals(requests.length, 1);
//   assert.match(requests[0].url, "/getbook");
// });
//});
describe('Testing MyLibrary  getbook using sinon', function() {
	beforeEach(function() {
	  this.xhr = sinon.useFakeXMLHttpRequest();
   
	  this.requests = [];
	  this.xhr.onCreate = function(xhr) {
		this.requests.push(xhr);
	  }.bind(this);
	});
   
	afterEach(function() {
	  this.xhr.restore();
	});
	it("which makes a GET request GetBook", function () {
		var callback = sinon.spy();
		getbook(callback);
	
		expect(callback.calledOnce).toEqual(true);
      //  this.requests[0].respond(200, { "Content-Type": "application/json" });
        
	  });
	
  });

