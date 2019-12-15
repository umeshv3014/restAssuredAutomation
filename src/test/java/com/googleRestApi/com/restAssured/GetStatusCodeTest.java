package com.googleRestApi.com.restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetStatusCodeTest {
	@BeforeClass
	public void setBaseUri() {

		RestAssured.baseURI = "https://maps.googleapis.com";
	}

	@Test
	public void testStatusCode() {

		Response res = given()

		.param("query", "restaurants in mumbai")
				.param("key", "AIzaSyASWyASvQGGkO8sRvYrqNjjgdznS2-BVxk")

				.when().get("/maps/api/place/Mumbai/json");

		Assert.assertEquals(res.statusCode(), 200);
	}

}
