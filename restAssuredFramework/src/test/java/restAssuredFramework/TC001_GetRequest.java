package restAssuredFramework;



import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.restasssured.constant.readPropertyFile;

public class TC001_GetRequest {

	
	//@Test
	void getWeatherDetails(){
		// Specify the base URL to the RESTful web service
    	//RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		
		RestAssured.baseURI = readPropertyFile.getInstance().getconfigProps().getProperty("baseURI");
		//RestAssured.baseURI = "https://uat.textblue911.com/demo/chat";
		
		// Creating request object httpRequest.
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		
		RequestSpecification httpRequest = RestAssured.given();
		
		// Creating response object 
		// Make a request to the server by specifying the method Type and the method URL.
	    // This will return the Response from the server. Store the response in a variable.
		
		//Response response = httpRequest.request(Method.GET, "/location?type=Web Rebid&chatSessionId=1146509");
		Response response = httpRequest.request(Method.GET,"/Hyderabad");
		
		// Now let us print the body of the message to see what response
		// we have received from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		// To capture and Verify Status code:
		int statusCode = response.getStatusCode();
		System.out.println("Status code is " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// To capture and verify Status Line:
		String statusLine = response.getStatusLine();
		System.out.println("Status line is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");		
		
	}
	
	    
        @Test
        void getCircuitDetails()
        {
	    RestAssured.baseURI = "http://ergast.com";
	    RequestSpecification httpRequest = RestAssured.given();
	    Response response = httpRequest.request(Method.GET,"/api/f1/2017/circuits.json");
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    // Read all the books as a List of String. Each item in the list
		// represent a book node in the REST service Response
		List<String> allCircuits = jsonPathEvaluator.getList("MRData.CircuitTable.Circuits.circuitName");

		// Iterate over the list and print individual circuits
		for(String circuit : allCircuits)
		{
			System.out.println("The Circuit Names are : " + circuit);
		}

		
	}

}
