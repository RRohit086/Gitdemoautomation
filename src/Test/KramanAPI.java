package Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class KramanAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://kotak-uat.hyperform.io";
		String response = given().auth().basic("ADMINMAKER", "default").header("Content-Type","application/json")
				.log().all()
				.body(Payload.createUpdate()).when().post("Incentivo/api/user/createOrUpdateUser")
				.then().assertThat().statusCode(200)
				.body("responseStatus", equalTo("Success")).extract().response()
				.asString();
		System.out.println(response);

		JsonPath js=new JsonPath(Payload.createUpdate());
		String loginId=js.getString("payload.domainId");
		System.out.println(loginId);

	}

}
