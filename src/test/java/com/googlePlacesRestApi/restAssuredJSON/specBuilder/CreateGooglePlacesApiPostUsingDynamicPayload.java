package com.googlePlacesRestApi.restAssuredJSON.specBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import enumApiResources.ApiResources;
import apiPayLoads.AddGooglePlaces;
import apiPayLoads.LocationGooglePlaces;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class CreateGooglePlacesApiPostUsingDynamicPayload {

	Properties prop;

	@BeforeTest
	public void readDataFromExternalFiles() throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test
	public void createGooglePlacesAPI() {
		ApiResources addPlaceApiResources = ApiResources.valueOf("AddPlaceAPIResources_JSON");
		AddGooglePlaces ap = new AddGooglePlaces();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		LocationGooglePlaces lp = new LocationGooglePlaces();
		lp.setLat(-38.383494);
		lp.setLng(33.427362);
		ap.setLocation(lp);
		List<String> arr = new ArrayList<String>();
		arr.add("shoe park");
		arr.add("shop");
		ap.setTypes(arr);
		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		Response res = given().queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(ap).when()
				.post(addPlaceApiResources.getApiResources())
				.then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and()
				.body("status", equalTo("OK")).extract().response();
		
		System.out.println(res.asString());

		// Create a place =response (place id)

		// delete Place = (Request - Place id)

	}
}
