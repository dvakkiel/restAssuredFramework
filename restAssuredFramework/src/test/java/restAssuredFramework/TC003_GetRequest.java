package restAssuredFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC003_GetRequest {
	@Test
	public void GoogleMapTest() {
		
	
	RestAssured.baseURI = "https://maps.gooleapis.com";	
	// Creating request object httpRequest.
	// Get the RequestSpecification of the request that you want to sent
	// to the server. The server is specified by the BaseURI that we have
	// specified in the above step.
	
	RequestSpecification httpRequest = RestAssured.given();	
	// Creating response object 
	// Make a request to the server by specifying the method Type and the method URL.
    // This will return the Response from the server. Store the response in a variable.
	
	
	Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");	
	// Now let us print the body of the message to see what response
	// we have received from the server
	String responseBody = response.getBody().asString();
	System.out.println("Response Body is =>  " + responseBody);
	
	// To get the Response Headers
	String contentLength = response.getHeader("content-length");
	Assert.assertEquals(contentLength, "614");
	

}

}
