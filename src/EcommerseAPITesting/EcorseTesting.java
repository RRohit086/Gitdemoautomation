package EcommerseAPITesting;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;

public class EcorseTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
.setContentType(ContentType.JSON).build();
LoginRequest loginrequest=new LoginRequest();
loginrequest.setUserEmail("postman@gmail.com");
loginrequest.setUserPassword("Hello123@");

	RequestSpecification loginrequestbody=given().log().all().spec(req).body(loginrequest);
	LoginResponce loginResponce=loginrequestbody.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponce.class);
	String token=loginResponce.getToken();
	System.out.println(loginResponce.getToken());
	System.out.println(loginResponce.getUserId());
	
	//Add Product
	CreateProductrespo createProductrespo=new CreateProductrespo();
	RequestSpecification addproduct= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token)
			.build();
	RequestSpecification reqaddprod= given().log().all().spec(addproduct).param("productName", "Laptop").param("productAddedBy", loginResponce.getUserId())
	.param("productCategory", "fashion").param("productSubCategory", "shirts").param("productPrice", "11500")
	.param("productDescription", "HP Originals").param("productFor", "women")
	.multiPart("productImage",new File("C://Users//rc611//Pictures//Screenshots//Laptop.png"));
	reqaddprod.when().post("/api/ecom/product/add-product").then().log().all().extract().response().as(CreateProductrespo.class);
	
	System.out.println(createProductrespo.getProductId());
	

	}

}
