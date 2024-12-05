package Test;

import io.restassured.RestAssured;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Pojo.Api;
import Pojo.GetCources;
import Pojo.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Ouathtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//RestAssured.baseURI="https://rahulshettyacademy.com";
String responce=given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type","client_credentials")
.formParams("scope","trust").when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
	.asString();
System.out.println(responce);
JsonPath js=new JsonPath(responce);
String accessToken=js.getString("access_token");
GetCources gc=given().queryParam("access_token", accessToken).when().log()
.all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCources.class);
System.out.println(gc.getLinkedIn());
System.out.println(gc.getInstructor());
System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());//using indexing
//not using indexing
List<Api>apicources=gc.getCourses().getApi();
for(int i=0;i<apicources.size();i++) {
	if(apicources.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
	System.out.println(apicources.get(i).getPrice());	 
	}
}
String[]courseTitle= {"Selenium Webdriver Java","Cypress","Protractor"};
ArrayList<String> a=new ArrayList<String>();
List<WebAutomation>webautomationcourse=gc.getCourses().getWebAutomation();
for(int j=0;j<webautomationcourse.size();j++) {
	System.out.println(webautomationcourse.get(j).getCourseTitle());
	a.add(webautomationcourse.get(j).getCourseTitle());
}
List<String>expectedList=Arrays.asList(courseTitle);
Assert.assertTrue(a.equals(expectedList));
	}
	

}
