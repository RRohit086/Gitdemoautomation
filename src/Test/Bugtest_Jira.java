package Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Bugtest_Jira {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
RestAssured.baseURI="https://raghuchaua.atlassian.net" ;
String createIssueresponce=given().header("Content-Type","application/json")
.header("Authorization","Basic cmM2MTExNzRAZ21haWwuY29tOkFUQVRUM3hGZkdGMEROUHRPWjg4OFNHS2lEQ3JKQU1MMmpVYVRlbkxndm1MR21jNV9EZU9IM0dQUGFYRG43UGQ3RnJNcHQ3czM1bThxU2JFemhWcm5ZdExsZnZtNkw3MmxnUVhGdUpib1hYcjhaUkhKU2ZyMU5iNTlrNVdiRlljN1VDNTk0N21xcXZUbG9UZkFJX0hEOUF5cldWY0JzWXlPNlMyUnV3X0RLaFNaUXhtTmRUR0NQZz0yMjczNTREOQ==")
.body("{\r\n"
		+ "    \"fields\": {\r\n"
		+ "       \"project\":\r\n"
		+ "       {\r\n"
		+ "          \"key\": \"SCRUM\"\r\n"
		+ "       },\r\n"
		+ "       \"summary\": \"KPS website is not working-automation.\",\r\n"
		+ "       \r\n"
		+ "       \"issuetype\": {\r\n"
		+ "          \"name\": \"Bug\"\r\n"
		+ "       }\r\n"
		+ "   }\r\n"
		+ "}").log().all().post("/rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();
	JsonPath js=new JsonPath(createIssueresponce);
	String responceId=js.get("id");
	System.out.println(responceId);
	//Add attachment
	given().pathParam("key",responceId).header("X-Atlassian-Token","no-check").header("Authorization","Basic cmM2MTExNzRAZ21haWwuY29tOkFUQVRUM3hGZkdGMEROUHRPWjg4OFNHS2lEQ3JKQU1MMmpVYVRlbkxndm1MR21jNV9EZU9IM0dQUGFYRG43UGQ3RnJNcHQ3czM1bThxU2JFemhWcm5ZdExsZnZtNkw3MmxnUVhGdUpib1hYcjhaUkhKU2ZyMU5iNTlrNVdiRlljN1VDNTk0N21xcXZUbG9UZkFJX0hEOUF5cldWY0JzWXlPNlMyUnV3X0RLaFNaUXhtTmRUR0NQZz0yMjczNTREOQ==")
	.multiPart("file",new File("C:/Users/rc611/Pictures/Screenshots/Screenshot 2024-10-08 232912.png"))
	.log().all().post("/rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}

}
