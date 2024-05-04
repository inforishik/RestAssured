package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.POJO_PostRequest;

//POJO---serilize---JsonObject---deserilize---POJO

public class SerilizartionDserilization 
{
	//POJO ==> JSON (Serilization)
	//@Test
	void pojo2json() throws JsonProcessingException
	{
		//create java object using pojo class
		Student stupojo=new Student();
		stupojo.setName("Scott");
	    stupojo.setLocation("France");
	    stupojo.setPhone("123456");
	    
	    String coursesArr[]={"C","C++"};
	    stupojo.setCourses(coursesArr);
	    
	    //convert java object----json object(serilization)
	    ObjectMapper objMapper=new ObjectMapper();
	    String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
	    System.out.println(jsondata);
	}
	
	//JSON TO POJO 
	@Test
	void convertJson2POJO() throws JsonMappingException, JsonProcessingException
	{
		String jsondata="{\r\n"
			  +	"  \"name\" : \"Scott\",\r\n"
			  +	"  \"location\" : \"France\",\r\n"
			  +	"  \"phone\" : \"123456\",\r\n"
			  +	"  \"courses\" : [ \"C\", \"C+\" ]\r\n"
			  +	"}";
		
		//json data to POJO object
		ObjectMapper objMapper=new ObjectMapper();
		Student stupojo=objMapper.readValue(jsondata,Student.class);
		
		System.out.println("Name:" +stupojo.getName());
		System.out.println("Location:" +stupojo.getLocation());
		System.out.println("Phone:" +stupojo.getPhone());
		System.out.println("Course:" +stupojo.getCourses()[0]);
		System.out.println("Course:" +stupojo.getCourses()[1]);
	}

}
