package com.googlePlacesRestApi.restAssuredJSON.specBuilder;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SearchNearByGooglePlacesGet {
	
	Properties prop;
	@BeforeTest
	public void readDataFromExternalFiles() throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test
	public void getPlaceDataJson() {
		// https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyAIlTfpdvRspg5NJk9zh0oGOtdwenrNTUI

		// setting the base url uri
		RestAssured.baseURI = prop.getProperty("googleEndPoing");

		// pass what all i have under given()
		given().param("location", "-33.8670522,151.1957362")
				.param("radius", "500")
				.param("type", "restaurant")
				.param("keyword", "cruise")
				.param("key", prop.getProperty("googlePlaceApiKey"))
				.
				// pass resources on get under when
				when()
				.get(ApiEndPointResources.getResourcesGooglePlaceJSON())
				.
				// to assert the response under then()
				then()
				.assertThat()
				.statusCode(200)
				.and()
				// putting validation on headers
				.contentType(ContentType.JSON)
				.and()
				.
				// assertion on body response json
				// //https://jsoneditoronline.org/
				// json path results[0].geometry.location.lat
				body("results[0].name", equalTo("Australian Cruise Group"))
				.and()
				.body("results[0].place_id",
						equalTo("ChIJrTLr-GyuEmsRBfy61i59si0")).and()
				.body("results[0].photos[0].width", equalTo(1024)).and().
				// to validate header
				header("server", "scaffolding on HTTPServer2");

	}
}
