package day4;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class ParsingJSONResponseData 
{
	@Test(priority=1)
	void testJSONResponse()
	{
		//Approach-1
		/*given()
		.contentType("ContentType/JSON")
		
		.when()
		.get("http://localhost:3000/store") 
		
		.then()
		.statusCode(200)
		.header("Content-Type","application/json")
		.body("book[3].title",equalTo("The Lord of the Rings"));*/
		
	Response res=
			given()
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store");
	
	/*Assert.assertEquals(res.statusCode(),200);
	Assert.assertEquals(res.header("Content-Type"),"application/json");
	
	String bookname=res.jsonPath().get("book[3].title").toString();
	Assert.assertEquals(bookname,"The Lord of the Rings");*/
	
	//JSON object class
	JSONObject jo=new JSONObject(res.asString()); //converting respose to json object type
	
	/*for(int i=0;i<jo.getJSONArray("book").length();i++)
	{
		String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
		System.out.println(bookTitle);
	}*/
	//Search for title of the book in json -validation 1
	boolean status=false;
	for(int i=0;i<jo.getJSONArray("book").length();i++)
	{
		String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		
		
		if(bookTitle.equals("The Lord of the Rings"))
		{
			status=true;
			break;
	    }
		
	}
	Assert.assertEquals(status,true);
	
	//Validate total price of the books
	double totalprice=0;
	for(int i=0;i<jo.getJSONArray("book").length();i++)
	{
		
		String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
	
		totalprice=totalprice+Double.parseDouble(price);
	}
	System.out.println("Total price of book=" +totalprice);
	Assert.assertEquals(totalprice,526.0);
	}

	

}
