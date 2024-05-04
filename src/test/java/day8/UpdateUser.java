package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser 
{
	@Test
	void test_updateUser(ITestContext context)
	{
		Faker faker=new Faker();
		JSONObject data=new JSONObject();
		
		data.put("name",faker.name().fullName());
		data.put("gender","Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status","active");
		
		String bearerToken="9e0d209286c7565d19bf855b082f528e84f4eb9de006af2539aad54de48373bd";
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		given()
		.headers("Authorization","Bearer "+bearerToken)
		.contentType("application/json")
		.body(data.toString())
		.pathParam("id",id)
		
		.when()
		.put("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
		.statusCode(200)
		.log().all();
		
		
		
	}

}
