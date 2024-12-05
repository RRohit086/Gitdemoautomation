package Test;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JsonPath js=new JsonPath(Payload.coursePrice());
System.out.println(js.getString("dashboard.purchaseAmount"));
int count=js.getInt("courses.size()");
for(int i=0;i<count;i++) {
	String title=js.getString("courses["+i+"].title");
	System.out.println(title);
}
	}

}
