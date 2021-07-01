package testCases;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DELETE_or_Delete_A_Product {
	SoftAssert softAssert = new SoftAssert();

	@Test
	public void delete_A_Product() {
		Response response = given().baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8").queryParams("id", "1474").when()
				.delete("/delete.php").then().extract().response();

		int statusCode = response.getStatusCode();
		System.out.println("Status code : " + statusCode);
		softAssert.assertEquals(statusCode, 200, "status code is not matchihg!");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body:" + responseBody);

		JsonPath js = new JsonPath(responseBody);
		String deleteMessage = js.getString("message");
		softAssert.assertEquals(deleteMessage , "Product was deleted.", "Product was not deleted");

		String productName = js.getString("name");
		System.out.println("ProductName:" + productName);
		softAssert.assertEquals(productName, "iphone Phone 40.0", "Product name is not matching");

		String productPrice = js.getString("price");
		System.out.println("ProductPrice:" + productPrice);
		softAssert.assertEquals(productPrice, "1200", "Product price is not matching");
		
		softAssert.assertAll();

		
	}

}
