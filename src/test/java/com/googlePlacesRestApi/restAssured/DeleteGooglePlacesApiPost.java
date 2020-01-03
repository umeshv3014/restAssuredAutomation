package com.googlePlacesRestApi.restAssured;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import apiPayLoads.bodyPayloads;
import com.google.places.resoureces.ApiEndPointResources;

import com.google.places.java.util.JavaUtil;
import com.google.places.resoureces.Constant;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class DeleteGooglePlacesApiPost {
	Properties prop;

	@BeforeTest
	public void configData() throws IOException {
		prop = JavaUtil.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test
	public void deleteMethod() {

		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		// create entity
		Response res = given()
				.queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(bodyPayloads.getPostDataAddPlace()).when()
				.post(ApiEndPointResources.getPostResourcesAddPlaces()).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("status", equalTo("OK")).extract().response();
		String responseJson = res.asString();

		JsonPath js = new JsonPath(responseJson);
		String placeId = js.get("place_id");

		// delete the created entity
		given().queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(bodyPayloads.getPostDataDeletePlace(placeId)).when()
				.post(ApiEndPointResources.getPostResourcesDeletePlaces()).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("status", equalTo("OK"));

	}
}
