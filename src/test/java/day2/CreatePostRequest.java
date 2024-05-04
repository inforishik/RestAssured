package day2;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class CreatePostRequest 
{
	//@Test(priority=1)
	void testPostusingHashmap()
	{
		HashMap data=new HashMap();
		
		data.put("name","Scott");
		data.put("location","France");
		data.put("phone","123456");
		
		String courseArr[]= {"C","C++"};
		data.put("courses",courseArr);
		
		given()
		.contentType("application/json")
		.body(data)
		
	    .when()
	    .post("http://localhost:3000/students")
	    
	    .then()
	    .statusCode(201)
	    .body("name",equalTo("Scott"))
	    .body("location",equalTo("France"))
	    .body("phone",equalTo("123456"))
	    .body("courses[0]",equalTo("C"))  
	    .body("courses[1]",equalTo("C++"))
	    .header("Content-type","application/json")
	    .log().all();
	    
	}
	
	//delete student record
	@Test(priority=5)
	void testDelete()
	{
		given()
		
        .when()
        .delete("http://localhost:3000/students/4")
        
        .then()
        .statusCode(404);
	}
	
	//Post request using org.json library
	//@Test(priority=2)
	void postRequestusingjsonlib()
	{
		JSONObject data=new JSONObject();
		
		data.put("name","Scott");
		data.put("location","France");
		data.put("phone","123456");
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
	    .when()
	    .post("http://localhost:3000/students")
	    
	    .then()
	    .statusCode(201)
	    .body("name",equalTo("Scott"))
	    .body("location",equalTo("France"))
	    .body("phone",equalTo("123456"))
	    
	    .header("Content-type","application/json")
	    .log().all();
	}
		
		//POST request body using POJO class
		//@Test(priority=3)
		void testPOSTusingPOJO()
		{
			POJO_PostRequest data=new POJO_PostRequest();
			data.setName("Scott");
		    data.setLocation("France");
		    data.setPhone("123456");
		    
		    String coursesArr[]={"C","C++"};
		    data.setCourses(coursesArr);
			
			given()
			.contentType("application/json")
			.body(data)
			
		    .when()
		    .post("http://localhost:3000/students")
		    
		    .then()
		    .statusCode(201)
		    .body("name",equalTo("Scott"))
		    .body("location",equalTo("France"))
		    .body("phone",equalTo("123456"))
		    .body("courses[0]",equalTo("C"))  
		    .body("courses[1]",equalTo("C++"))
		    .header("Content-type","application/json")
		    .log().all();
			
	}
		//POST request body using external json file
				@Test(priority=4)
				void testPOSTusingExternalJson() throws FileNotFoundException
				{
					File f=new File(".\\body.json");
					FileReader fr=new FileReader(f);
					JSONTokener jt=new JSONTokener(fr); 
					JSONObject data=new JSONObject(jt);
					
					given()
					.contentType("application/json")
					.body(data.toString())
					
				    .when()
				    .post("http://localhost:3000/students")
				    
				    .then()
				    .statusCode(201)
				    .body("name",equalTo("Scott"))
				    .body("location",equalTo("France"))
				    .body("phone",equalTo("123456"))
				    .body("courses[0]",equalTo("Ruby"))  
				    .body("courses[1]",equalTo("Python"))
				    .header("Content-type","application/json")
				    .log().all();
					
			}


}
