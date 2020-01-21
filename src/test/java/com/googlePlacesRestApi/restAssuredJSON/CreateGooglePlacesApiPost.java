package com.googlePlacesRestApi.restAssuredJSON;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import enumApiResources.ApiResources;
import testDataBuild.JSONbodyPayloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class CreateGooglePlacesApiPost {
	
	Properties prop;
	@BeforeTest
	public void readDataFromExternalFiles() throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test
	public void createGooglePlacesAPI(){
		ApiResources addPlaceApiResources = ApiResources.valueOf("AddPlaceAPIResources_JSON");
		
		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		given().
		queryParam("key", prop.getProperty("qaclickApiKey")).
		body(JSONbodyPayloads.getPostDataAddPlace()).
		when().
		post(addPlaceApiResources.getApiResources()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	    body("status", equalTo("OK"));

		// Create a place =response (place id)

		// delete Place = (Request - Place id)

	}
}
