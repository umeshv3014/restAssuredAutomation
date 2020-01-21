package com.googlePlacesRestApi.restAssuredJSON.specBuilder;

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
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test
	public void deleteMethod() {
		JsonPath responseJson;
		ApiResources addPlaceApiResources = ApiResources.valueOf("AddPlaceAPIResources_JSON");
		ApiResources deletePlaceApiResources = ApiResources.valueOf("DeletePlaceAPIResources_JSON");

		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		// create entity
		Response res = given()
				.queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(JSONbodyPayloads.getPostDataAddPlace()).when()
				.post(addPlaceApiResources.getApiResources()).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("status", equalTo("OK")).extract().response();
		responseJson = 	ApiUtil.rawToJson(res);
		String placeId = responseJson.get("place_id");

		// delete the created entity
		given().queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(JSONbodyPayloads.getPostDataDeletePlace(placeId)).when()
				.post(deletePlaceApiResources.getApiResources()).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("status", equalTo("OK"));

	}
}
