package Test;

import org.testng.annotations.DataProvider;

public class Datafortesting {
	
	@DataProvider(name="booksData")
	public  Object[][] getData() {
		//array=collection of element
		//multidiamentional array=collection of array
		return new Object[][] { {"Roit1","1234"},{"Rhit2","2344"},{"Roht3","3445"}};
	}
}
