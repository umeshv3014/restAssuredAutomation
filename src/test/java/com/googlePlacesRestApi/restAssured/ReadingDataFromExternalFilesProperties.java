package com.googlePlacesRestApi.restAssured;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.places.java.util.JavaUtil;
import com.google.places.resoureces.Constant;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadingDataFromExternalFilesProperties {
	Properties prop;

	@BeforeTest
	public void readDataFromExternalFiles() throws IOException {
		prop = JavaUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test
	public void postDeletePlace() {
		// https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyAIlTfpdvRspg5NJk9zh0oGOtdwenrNTUI

		// setting the base url uri
		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");

		// pass what all i have under given()
		// task 1 : grab the response
		Response res = given()
				.queryParam("key", prop.getProperty("qaclickApiKey"))
				.body("{"
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
						+ "\"language\": \"en-AU\"" + "}").when()
				.post("/maps/api/place/add/json").then().assertThat()
				.statusCode(Constant.RESPONSE_STATUS_CODE_200).and()
				// putting validation on headers
				.contentType(ContentType.JSON).and()
				.body("status", equalTo("OK")).
				// to extract the response
				extract().response();

		String resString = res.asString();
		System.out.println(resString);
		// task 2 grab the place_id from the response
		// convert the extracted jsonString to json
		JsonPath js = new JsonPath(resString);
		String placeID = js.get("place_id");
		System.out.println(placeID);
		// task 3 place this place id in delte request
		given().queryParam("key", prop.getProperty("qaclickApiKey"))
				.body("{" + "\"place_id\": \"" + placeID + "\"" + "}").when()
				.post("/maps/api/place/delete/json").then().assertThat()
				.statusCode(Constant.RESPONSE_STATUS_CODE_200).and()
				// putting validation on headers
				.contentType(ContentType.JSON).and()
				.body("status", equalTo("OK"));

	}
}
