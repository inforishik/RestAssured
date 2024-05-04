package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import groovy.util.logging.Log;
public class LoggingDemo 
{
	@Test
	void testlogs()
	{
		given()
		
		.when()
		.get("http://localhost:3000/")
		 
		.then()
		//.log().all();
		//
		//.log().cookies();
		.log().headers();
		
	}

}
