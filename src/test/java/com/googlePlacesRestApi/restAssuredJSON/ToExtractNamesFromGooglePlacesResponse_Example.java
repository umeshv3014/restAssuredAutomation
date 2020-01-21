package com.googlePlacesRestApi.restAssuredJSON;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import enumApiResources.ApiResources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ToExtractNamesFromGooglePlacesResponse_Example {

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
		ApiResources addPlaceApiResources = ApiResources.valueOf("GetPlaceAPIResources_JSON");

		// pass what all i have under given()
		Response res = given().param("location", "-33.8670522,151.1957362")
				.param("radius", "500").param("type", "restaurant")
				.param("keyword", "cruise")
				.param("key", prop.getProperty("googlePlaceApiKey")).when()
				.get(addPlaceApiResources.getApiResources()).then()
				.assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().extract().response();

		JsonPath jpath = ApiUtil.rawToJson(res);
		int size = jpath.get("results.size()");
		
		for (int i = 0; i < size; i++) {
			System.out.println(jpath.get("results["+i+"].name"));
		}
	

	}
}
