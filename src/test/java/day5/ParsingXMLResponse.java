package day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse 
{
	@Test
	void testXMLRespose()
	{
		//approach-1
		/*given()
		
		.when()
		.get("https://thetestrequest.com/authors.xml")
		
		.then()
		.statusCode(200)
		.header("Content-Type","application/xml; charset=utf-8")
		.body("objects.object.name[0]",equalTo("Karl Zboncak"));*/
		
		//Approach-2
		Response res=
				given()
				
				.when()
				.get("https://thetestrequest.com/authors.xml");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
		
	String name=res.xmlPath().get("objects.object.name[0]").toString();
	Assert.assertEquals(name,"Karl Zboncak");
	}
	
	@Test
	void testXMLResponseBody()
	{
		Response res=
				given()
				
				.when()
				.get("https://thetestrequest.com/authors.xml");
		
		XmlPath xmlobj=new XmlPath(res.asString());
		List<String> objects=xmlobj.getList("objects.object");
		
		Assert.assertEquals(objects.size(),5);
		
		//verify object name is present in response
		List<String> objects_names=xmlobj.getList("objects.object.name");
		
		for(String object_name:objects_names)
		{
			
			System.out.println(object_name);
		}
		
		boolean status=false;
		for(String object_name:objects_names)
		{
			if(object_name.equals("Karl Zboncak"))
			{
				status=true;
				break;
			}
			
		}
		Assert.assertEquals(status,true);
	
	}
}
