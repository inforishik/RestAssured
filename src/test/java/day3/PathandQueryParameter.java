package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class PathandQueryParameter 
{
	@Test
	void testQueryandPathparameters()
	{
		given()
		.pathParam("mypath","users")   //path parameters
		.queryParam("Page","2")        //query parameters
		.queryParam("id","5")          //query parameters
		
		.when()
		.get("https://reqres.in/api/{mypath}")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
	

}
