package com.googlePlacesRestApi.restAssuredJSON.specBuilder;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import apiPayLoads.JSONbodyPayloads;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
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
	public void createGooglePlacesAPIusingSpecBuilder() {

		// RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(prop.getProperty("qaclickEndPoint"))
				.setContentType(ContentType.JSON).build();

		ResponseSpecification res = new ResponseSpecBuilder()
				.expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();

		given().spec(req).body(JSONbodyPayloads.getPostDataAddPlace()).when()
				.post(ApiEndPointResources.getPostResourcesAddPlacesJSON())
				.then().spec(res).and().body("status", equalTo("OK"));
	}
}
