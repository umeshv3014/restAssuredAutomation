package com.googlePlacesRestApi.restAssuredXML;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import apiPayLoads.JSONbodyPayloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class CreateGooglePlacesApiPost {

	Properties prop;
	String xmlPayLoads;

	@BeforeTest
	public void readDataFromExternalFiles() throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
		xmlPayLoads = ApiUtil
				.generateStringFromResource(Constant.CONFIGURATION_PAYLOAD_XML);
	}

	@Test
	public void createGooglePlacesAPI() {

		RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		Response res = given()
				.queryParam("key", prop.getProperty("qaclickApiKey"))
				.body(xmlPayLoads).when()
				.post(ApiEndPointResources.GetPostResourcesAddPlacesXML())
				.then().assertThat().statusCode(200).and()
				.contentType(ContentType.XML).and().extract().response();

		XmlPath resp = ApiUtil.rawToXML(res);
		String status = resp.get("response.status");
		String placeId = resp.getString("response.place_id");
		System.out.println(status + "" + placeId);

	}
}
