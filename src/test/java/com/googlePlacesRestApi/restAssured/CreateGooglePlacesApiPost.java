package com.googlePlacesRestApi.restAssured;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.places.java.util.JavaUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import apiPayLoads.bodyPayloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class CreateGooglePlacesApiPost {
	
	Properties prop;
	@BeforeTest
	public void readDataFromExternalFiles() throws IOException {
		prop = JavaUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test
	public void createGooglePlacesAPI(){
		
		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		given().
		queryParam("key", prop.getProperty("qaclickApiKey")).
		body(bodyPayloads.getPostDataAddPlace()).
		when().
		post(ApiEndPointResources.getPostResourcesAddPlaces()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	    body("status", equalTo("OK"));

		// Create a place =response (place id)

		// delete Place = (Request - Place id)

	}
}
