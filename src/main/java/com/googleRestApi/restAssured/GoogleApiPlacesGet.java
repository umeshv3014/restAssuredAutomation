package com.googleRestApi.restAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class GoogleApiPlacesGet {

	@Test
	public static void api() {
		// https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyAIlTfpdvRspg5NJk9zh0oGOtdwenrNTUI
		// base url or host
		RestAssured.baseURI = "https://maps.googleapis.com";

		// get request
		given().param("location", "-33.8670522,151.1957362")
				.param("radius", "500")
				.param("key", "AIzaSyAIlTfpdvRspg5NJk9zh0oGOtdwenrNTUI").when()
				.get("/maps/api/place/nearbysearch/json").then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.
				// body("results[0].geometry.location.lat",
				// equalTo("-33.85717357010728"));
				body("results[0].name", equalTo("Sydney")).and()
				.header("server", "scaffolding on HTTPServer2");

	}

}
