package testCases;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PUT_or_Update_A_Product {
	SoftAssert softAssert = new SoftAssert();

	
	  @Test public void update_A_Product() {
	  
	  HashMap payload = new HashMap(); payload.put("id", "2045");
	  payload.put("name", "Flying House"); payload.put("price", "700000");
	  payload.put("description", "Future Inovation"); payload.put("category_id",
	  "3");
	  
	  Response response = given()
	  .baseUri("https://techfios.com/api-prod/api/product") .header("Content-Type",
	  "application/json; charset=UTF-8") .body(payload) .when() .put("/update.php")
	  .then() .extract().response();
	  
	  int statusCode = response.getStatusCode();
	  System.out.println("Status code : " + statusCode);
	  softAssert.assertEquals(statusCode, 200);
	  
	  String responeHeader = response.header("Content-Type");
	  softAssert.assertEquals(responeHeader, "application/json; charset=UTF-8");
	  
	  String responeContentLength = response.header("Content-Length");
	  softAssert.assertEquals(responeContentLength, "34");
	  
	  String responseBody = response.getBody().asString();
	  System.out.println("Response Body:" + responseBody);
	  
	  JsonPath js = new JsonPath(responseBody); String successMessage =
	  js.getString("message"); System.out.println("Success Messsage:" +
	  successMessage); softAssert.assertEquals(successMessage,
	  "Product was updated.", "Product was not updated"); }
	 
	
	  @Test public void read_A_Product() { Response response = given()
	  .baseUri("https://techfios.com/api-prod/api/product") .header("Content-Type",
	  "application/json; charset=UTF-8") .queryParams("id", "2045") .when()
	  .get("/read_one.php") .then().extract().response();
	  
	  int statusCode = response.getStatusCode();
	  System.out.println("Status code : " + statusCode);
	  softAssert.assertEquals(statusCode, 200);
	  
	  String responseBody = response.getBody().asString();
	  System.out.println("Response Body:" + responseBody);
	  
	  JsonPath js = new JsonPath(responseBody); String productId =
	  js.getString("id"); System.out.println("ProductId:" + productId);
	  softAssert.assertEquals(productId, "2045", "Product id is not matching");
	  
	  String productName = js.getString("name"); System.out.println("ProductName:"
	  + productName); softAssert.assertEquals(productName, "Flying House",
	  "Product name is not matching");
	  
	  String productPrice = js.getString("price");
	  System.out.println("ProductPrice:" + productPrice);
	  softAssert.assertEquals(productPrice, "700000",
	  "Product price is not matching");
	  
	  softAssert.assertAll();
	  
	  }
	 

}
