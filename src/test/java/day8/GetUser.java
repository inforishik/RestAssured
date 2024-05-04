package day8;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;
public class GetUser 
{
	@Test
	void test_getuser(ITestContext context)
	{
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		String bearerToken="9e0d209286c7565d19bf855b082f528e84f4eb9de006af2539aad54de48373bd";
		
		given()
		.header("Authorization","Bearer "+bearerToken)
		.pathParam("id",id)
		
		.when()
		.get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
		.statusCode(200)
		.log().all();
	}

}
