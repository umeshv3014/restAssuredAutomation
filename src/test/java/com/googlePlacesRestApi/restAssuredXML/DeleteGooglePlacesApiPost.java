package com.googlePlacesRestApi.restAssuredXML;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testDataBuild.JSONbodyPayloads;

import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.Constant;

import enumApiResources.ApiResources;
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
		prop = ApiUtil.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test
	public void deleteMethod() {

		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		ApiResources addPlaceApiResources = ApiResources.valueOf("AddPlaceAPIResources_JSON");
		ApiResources deletPlaceApiResources = ApiResources.valueOf("DeletePlaceAPIResources_JSON");
		// create entity
		Response res = given()
				.queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(JSONbodyPayloads.getPostDataAddPlace()).when()
				.post(addPlaceApiResources.getApiResources()).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("status", equalTo("OK")).extract().response();
		String responseJson = res.asString();

		JsonPath js = new JsonPath(responseJson);
		String placeId = js.get("place_id");

		// delete the created entity
		given().queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(JSONbodyPayloads.getPostDataDeletePlace(placeId)).when()
				.post(deletPlaceApiResources.getApiResources()).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("status", equalTo("OK"));

	}
}
