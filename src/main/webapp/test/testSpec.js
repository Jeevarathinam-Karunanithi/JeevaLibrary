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
describe("A suite", function(){
    var a = true;
	it("can be declared by calling 'pending' in the spec body", function() {
		expect(true).toBe(false);
		pending('this is why it is pending');
	  });
	xit(" with xit",function(){
        expect(a).not.toBe(false);
	});
});