package com.googleRestApi.restAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javaConstants.Constant;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class GoogleApiPlacesDeletePost {
	Properties prop = new Properties();

	@BeforeTest
	public void configData() throws IOException {
		FileInputStream fs = new FileInputStream(
				Constant.CONFIGURATION_PROPERTIES);
		prop.load(fs);
	}

	@Test
	public void deleteMethod() {
		String addPlacePlayload = "{"
				+ "\"location\": {"
				+ "\"lat\": -33.8669710,"
				+ "\"lng\": 151.1958750"
				+ "},"
				+ "\"accuracy\": 50,"
				+ "\"name\": \"Google Shoes!\","
				+ "\"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","
				+ "\"types\": [\"shoe_store\"],"
				+ "\"website\": \"http://www.google.com.au/\","
				+ "\"language\": \"en-AU\"" + "}";
		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		// create entity
		Response res = given()
				.queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(addPlacePlayload).when()
				.post(prop.getProperty("postAddPlaceEndPoinResources")).then()
				.assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and()
				.body("status", equalTo("OK")).extract().response();
		String responseJson = res.asString();
		System.out.println(responseJson);

		JsonPath js = new JsonPath(responseJson);
		String placeId = js.get("place_id");
		System.out.println(placeId);

		// delete the created entity
		given().queryParam("key", prop.getProperty("qaclickApiKey"))
				.body("{" + "\"place_id\":\"" + placeId + "\"" + "}").when()
				.post(prop.getProperty("postDeletePlaceEndPoinResources"))
				.then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and()
				.body("status", equalTo("OK"));

	}
}
