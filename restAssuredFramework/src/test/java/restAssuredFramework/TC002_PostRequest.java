package restAssuredFramework;


	import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

	import io.restassured.RestAssured;
	import io.restassured.http.Method;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;

	
	public class TC002_PostRequest {
		@Test
		void postRegistrationSuccessful()
		{
			// Specify the base URL to the RESTful web service
	    	//RestAssured.baseURI = "http://restapi.demoqa.com/customer";
			//RestAssured.baseURI = "https://uat.textblue911.com/demo/chat";
	    	RestAssured.baseURI = "https://reqbin.com/echo";
			
			// Creating request object httpRequest.
			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			
			RequestSpecification httpRequest = RestAssured.given();
			
			// Creating response object 
			// Make a request to the server by specifying the method Type and the method URL.
		    // This will return the Response from the server. Store the response in a variable.
			
			// Request payload to be sent with POST Request method.
			JSONObject requestParams = new JSONObject();
			
			requestParams.put("Id", "78912");
			requestParams.put("Customer","Jason Sweet");
			requestParams.put("Quantity","1");
			requestParams.put("Price","18.00");
			httpRequest.header("Content-Type","application/json");
			
			// attach the parameter data to the request.
			httpRequest.body(requestParams.toJSONString());
			
			Response response = httpRequest.request(Method.POST,"/post/json");
			// Now let us print the body of the message to see what response
			// we have received from the server
			String responseBody = response.getBody().asString();
			System.out.println("Response Body is =>  " + responseBody);
			
			// To capture and Verify Status code:
			int statusCode = response.getStatusCode();
			System.out.println("Status code is " + statusCode);
			Assert.assertEquals(statusCode, 200);
			
			
			// Success Code validation.
			String successCode = response.jsonPath().get("success");
			System.out.println("The success code is " + successCode);
			Assert.assertEquals(successCode, "true");
		}

	


}
