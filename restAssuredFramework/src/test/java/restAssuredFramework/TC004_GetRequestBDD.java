package restAssuredFramework;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

// Hamcrest library matchers to be imported for getting options with given/when /then etc.
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class TC004_GetRequestBDD {
	
	//@Test
	public void noOfCircuits(){
		given().log().all().
		when().
		get("http://ergast.com/api/f1/2017/circuits.json").
		then().
		assertThat().
		statusCode(200).
		and().
		//Queries for all elements called circuitId 
		//using the Groovy GPath expression "MRData.CircuitTable.Circuits.circuitId"
		body("MRData.CircuitTable.Circuits.circuitId", hasSize(20)).
		body("Location", hasItems("locality")).
		// The hasSize() method is from hamcrest library 
		and().		
        contentType(ContentType.JSON).
        and().
		header("content-length",equalTo("4551"));
		}
	
//Sending request query parameters for a GET CALL ie. after ? in API call.
//https://uat.textblue911.com/demo/CAPI?cellNum=3472010453&action=2&msgBody=CAPI-test&userID=474&psapID=69&passCode=test
	//@Test
	public void oldCapiInitiateCall() {
	    
	    String cellNum = "3472010453";
	    String action = "2";
	    String msgBody = "Hello there Bluemuffins";
	    String userId = "474";
	    String psap_id = "69";
	    String passcode = "test";	        
	    given().
	        param("cellNum",cellNum).
	        param("action", action).
	        param("msgBody",msgBody).
	        param("userID",userId).
	        param("psapID",psap_id).
	        param("passCode",passcode).
	    when().
	        get("http://uat.textblue911.com/demo/CAPI").
	        then().
			assertThat().
			statusCode(200);
			
	}
		
// Sending messages using path parameters for a GET CALL.
// In http://ergast.com/api/f1/2017/circuits.json, "2017" is a path parameter
	//@Test
	public void checkPathParameterCase() {
		String season = "2017";
		int numberOfRaces = 20;
		given().
		pathParam("raceSeason",season).
		when().
		get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
		then().
		assertThat().
		body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
		
	}

	//Sending request with parameters for a GET CALL  in API call.
	//@Test
    public void newCapiInitiateCall() {
	    
	    String phone = "7852749412";
	    String msgBody = "Hello Butterflies in sky winds down and flirt up";
	    String psap_id = "69";
	    	        
	    given().
	        param("message",msgBody).
	        param("phone", phone).
	        param("psapId", psap_id).
	    	when().
	    post("https://uat.textblue911.com/demo/chat/initiate").
	        then().
		assertThat().
		//body("username", equalTo("sergey")).
		and().
		statusCode(200);
			
	}
	
	// Parameterization using Data Provider method in below API call.
	//  https://ergast.com/api/f1/2010/2/drivers/button
	@DataProvider(name ="season, lap, driverinfo")
	public Object[][] createTestDataRecords() {
	    return new Object[][] {
	        {"2010",1,"vettel"},
	        {"2010",3,"räikkönen"},	
	        {"2010",2,"button"}
	    };
	}
	@Test(dataProvider="season, lap, driverinfo")
	public void parameterisedTest(String season, int lap, String driverId)
	{
		given().
		given().log().all().
        pathParam("raceSeason",season).
        pathParam("lap",lap).
        pathParam("driverid",driverId).
    when().
        get("https://ergast.com/api/f1/{raceSeason}/{lap}/drivers/{driverid}.json").
   then().
      assertThat().
      body("MRData.DriverTable.Drivers.driverId",containsString("driverId"));
      
	
      
	}
}
