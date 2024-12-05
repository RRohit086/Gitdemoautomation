package Serialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress("Jhansi like USA");
		p.setLanguage("Hindi");
		p.setPhone_number("9922343434");
		p.setName("Rohit chauhan");
		p.setWebsite("https:rohiteducation.com");
		List<String>mylist=new ArrayList<String>();
		mylist.add("rohit");
		mylist.add("ragini");
		p.setTypes(mylist);
		Location l=new Location();
		l.setLat(40.33);
		l.setLng(45.77);
		p.setLocation(l);
		RestAssured.baseURI="https://rahulshettyacademy.com";
String stringres=given().queryParam("key","qaclick123").body(p).when()
.post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
	
	System.out.println(stringres);
	}

}
