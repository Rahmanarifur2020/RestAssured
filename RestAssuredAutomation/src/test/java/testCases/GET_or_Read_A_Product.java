package testCases;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GET_or_Read_A_Product {
	SoftAssert softAssert = new SoftAssert();

	@Test
	public void read_A_Product() {
		Response response = given().baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8").queryParams("id", "1474").when()
				.get("/read_one.php").then().extract().response();

		int statusCode = response.getStatusCode();
		System.out.println("Status code : " + statusCode);
		// Assert.assertEquals(statusCode, 200);
		softAssert.assertEquals(statusCode, 200);

//		  long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
//		  System.out.println("Response Time:" + responseTime ); if(responseTime<=2000)
//		  { System.out.println("Response time is within range!");
//		  } 
//		  else {
//		  System.out.println("Response time is out of range!"); 
//		  }

		String responseBody = response.getBody().asString();
		System.out.println("Response Body:" + responseBody);

		JsonPath js = new JsonPath(responseBody);
		String productId = js.getString("id");
		System.out.println("ProductId:" + productId);
		// Assert.assertEquals(productId, "1474");
		softAssert.assertEquals(productId, "1474", "Product id is not matching");

		String productName = js.getString("name");
		System.out.println("ProductName:" + productName);
		// Assert.assertEquals(productName, "iphone Phone 40.0");
		softAssert.assertEquals(productName, "iphone Phone 40.0", "Product name is not matching");

		String productPrice = js.getString("price");
		System.out.println("ProductPrice:" + productPrice);
		// Assert.assertEquals(productPrice, "1200");
		softAssert.assertEquals(productPrice, "1200", "Product price is not matching");

		softAssert.assertAll();

		// System.out.println("Response Body :" + response.getBody().asString());
	}

}
