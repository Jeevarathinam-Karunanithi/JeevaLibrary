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

// describe("Test for getbook",function(){

// 	beforeEach(function() {
// 		jasmine.Ajax.install();
// 		loadFixtures('library.jsp');
// 	    loadDoc();
// 	  });
// 	  afterEach(function() {
// 		jasmine.Ajax.uninstall();
// 	  });
	   
// // 	  it("specifying response when you need it", function() {
// // 		var doneFn = jasmine.createSpy("success");
// // 	  expect(jasmine.Ajax.requests.getBook()).toBe('/getbook ');
// //       expect(doneFn).not.toHaveBeenCalled();
// // });
// 	});