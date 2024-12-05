package Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class DyanamicJson {
	
@Test(dataProvider="booksData")
public void addBook(String isbn,String aisl) {
	RestAssured.baseURI="http://216.10.245.166";
	String responce= given().header("Content-Type","application/json").body(Payload.addBook(isbn,aisl)).when().post("/Library/Addbook.php")
	.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js=ReusableMethod.rawToJson(responce);
	String sid=js.get("ID");
	System.out.println(sid);
	
	
}
@DataProvider(name="booksData")
public  Object[][] getData() {
	//array=collection of element
	//multidiamentional array=collection of array
	return new Object[][] { {"Rohit1","123"},{"Rohit2","234"},{"Rohit3","345"}};
}

	
}

