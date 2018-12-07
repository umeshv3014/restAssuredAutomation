package com.googleRestApi.com.restAssured;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class GetStatusCodeTest {
	@BeforeClass
	public void setBaseUri() {

		RestAssured.baseURI = "https://maps.googleapis.com";
	}

	@Test
	public void testStatusCode() {

		Response res = given()

		.param("query", "restaurants in mumbai").param("key", "AIzaSyASWyASvQGGkO8sRvYrqNjjgdznS2-BVxk")

		.when().get("/maps/api/place/Mumbai/json");

		Assert.assertEquals(res.statusCode(), 200);
	}

}
