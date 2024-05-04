package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class HeaderDemo 
{
	//@Test
	void testHeaders()
	{
	
	given()
	
	.when()
	.get("https://www.amazon.com/")
	
	.then()
	.header("Content-Type","text/html; charset=ISO-8859-1")
	.and()
	.header("Content-Encoding","gzip")
	.and()
	.header("Server","gws");
	
}
	@Test
	void getHeaders()
	{
	
	Response res=given()
	
	.when()
	.get("https://www.google.com/");
	
	//get sigle header info
String headervalue=res.getHeader("Content-Type");
System.out.println("Content Type value=" +headervalue);

//get all header info
Headers myheaders=res.getHeaders();

for(Header hd:myheaders)
{
	System.out.println(hd.getName()+"   "+hd.getValue());
}
	
	
	
}
}