package Test;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Base {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();
		System.out.println(response);

		JsonPath js = new JsonPath(response);// for parsing Json
		String PlaceID = js.getString("place_id");// there is no parent for place id
		System.out.println(PlaceID);

		// update api
		
		String newAddress="Jhansi toriya";
		String updatedresponse = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\""+PlaceID+"\",\r\n"
						+ "\"address\":\""+newAddress+"\",\r\n" + "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).header("Server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
		System.out.println(updatedresponse);
		
		//get api validation
		String getplaceresponce=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", PlaceID)
		.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().asString();
		JsonPath js1=ReusableMethod.rawToJson(getplaceresponce);
		String updatedstring=js1.getString("address");
		System.out.println(updatedstring);
		Assert.assertEquals(updatedstring,newAddress);
		
		//delete API
		
	}

}
