package day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadandDownload 
{
	@Test(priority=1)
	void sigleFileUpload()
	{
		File myfile=new File("C:\\Automation\\text.txt");
		given()
		.multiPart("file",myfile)
		.contentType("multipart/form-data")
		
		.when()
		.post("http://localhost:8080/uploadFile")
		
		.then()
		.statusCode(200)
		.body("fileName",equalTo("text.txt"))
		.log().all();
	}
	
//	@Test
	void multipleFilesUpload()
	{
		File myfile1=new File("C:\\Automation\\text.txt");
		File myfile2=new File("C:\\Automation\\text2.txt");
		given()
		.multiPart("files",myfile1)
		.multiPart("files",myfile2)
		.contentType("multipart/form-data")
		
		.when()
		.post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
		.statusCode(200)
		.body("[0].fileName",equalTo("text.txt"))
		.body("[1].fileName",equalTo("text2.txt"))
		.log().all();
	}
	
	@Test(priority=2)
	void filedownload()
	{
		given()
		
		.when()
		.get("http://localhost:8080/downloadFile/text.txt")
		
		.then()
		.statusCode(200)
		.log().body();
		
	}

}
