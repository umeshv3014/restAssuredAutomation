package com.googlePlacesRestApi.restAssuredJSON.specBuilder;

import org.testng.annotations.Test;

import com.api.automation.base.util.Base;
import com.google.places.api.util.RequestResponseSpecBuilders;
import com.google.places.resoureces.ApiEndPointResources;

import apiPayLoads.JSONbodyPayloads;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class CreateGooglePlacesApiPost extends Base {
	protected RequestSpecification req;
	protected ResponseSpecification res;

	@Test
	public void createGooglePlacesAPIusingSpecBuilder() {
		
		 req =	RequestResponseSpecBuilders.requestSpecification(prop.getProperty("qaclickEndPoint"), ContentType.JSON);
		 res = RequestResponseSpecBuilders.responseSpecification(200, ContentType.JSON);
		// RestAssured.baseURI = prop.getProperty("qaclickEndPoint");
		

		given().spec(req).body(JSONbodyPayloads.getPostDataAddPlace()).when()
				.post(ApiEndPointResources.getPostResourcesAddPlacesJSON())
				.then().spec(res).and().body("status", equalTo("OK"));
	}
}
