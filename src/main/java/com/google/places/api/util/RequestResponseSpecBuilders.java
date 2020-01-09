package com.google.places.api.util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecBuilders {

	public static RequestSpecification reqSpecBuilder(String uri, ContentType type) {
		RequestSpecification req = new RequestSpecBuilder().setBaseUri(uri)
				.setContentType(type).build();
		return req;
	}

	public static ResponseSpecification respSpecBuilder(int statusCode,
			ContentType type) {
		ResponseSpecification res = new ResponseSpecBuilder()
				.expectStatusCode(statusCode).expectContentType(type)
				.build();
		return res;
	}
}
