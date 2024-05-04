package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo 
{
	//@Test(priority=1)
	void testCookies()
	{
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.cookie("AEC","Ae3NU9P3HRH4lqiWBAQiZ71V5KOAISZrnh3PgkFYLoBtlijWW2aWqCbsWfE")
		.log().all();
		
	}
	
	@Test(priority=2)
	void testCookiesInfo()
	{
		Response res=given()
		
		.when()
		.get("https://www.google.com/");
		
     //single cookie info
		// String cookie_value=res.getCookie("AEC");
		 //System.out.println("Value of cookie=" +cookie_value);
		
		//get all cookies info
		Map<String,String> cookies_values=res.getCookies();
		System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet())
		{
			String cookie_value=res.getCookie(k);
			System.out.println(k+"  "+cookie_value);
		}

}
}