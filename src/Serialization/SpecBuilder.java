package Serialization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.RequestBuilder;

public class SpecBuilder {

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
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		RestAssured.baseURI="https://rahulshettyacademy.com";
RequestSpecification res=given().spec(req).body(p);
String stringres=res.when()
.post("/maps/api/place/add/json").then().spec(resp).extract().response().asString();
	
	System.out.println(stringres);
	}

}
