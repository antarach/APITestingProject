package com.test.spaceX;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class SpaceXTest {

	private static final String baseURL = "https://api.spacexdata.com/v4/launches/latest";

	@Test
	public void testSuccessfullGET() {
		Response response = RestAssured.get(baseURL);
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test
	public void testExpectedImageinGET() {
		given().when().get(baseURL).then().statusCode(200).assertThat().body("links.patch.small",
				equalTo("https://images2.imgbox.com/eb/0f/Vev7xkUX_o.png"));
	}

	@Test
	public void testExpectedShipsinGET() {
		given().when().get(baseURL).then().statusCode(200).assertThat().body("ships", hasSize(5));
	}

	@Test
	public void testfailPOST() {
		Response response = RestAssured.post(baseURL);
		Assert.assertEquals(response.statusCode(), 404);
	}

	@Test
	public void testfailPUT() {
		Response response = RestAssured.put(baseURL);
		Assert.assertEquals(response.statusCode(), 404);
	}

	@Test
	public void testSucessHEAD() {
		Response response = RestAssured.head(baseURL);
		Assert.assertEquals(response.statusCode(), 200);
	}
}
