package day8;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;
public class DeleteUser 
{
	@Test
	void test_deletUser(ITestContext context)
	{
		String bearearToken="9e0d209286c7565d19bf855b082f528e84f4eb9de006af2539aad54de48373bd";
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		given()
		.headers("Authorization","Bearer "+bearearToken)
		.pathParam("id",id)
		.when()
		.delete("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
		.statusCode(204)
		.log().all();
	
	}

}
