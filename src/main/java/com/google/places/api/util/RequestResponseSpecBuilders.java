package com.google.places.api.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecBuilders {

	public static RequestSpecification req;
	public static ResponseSpecification res;

	public static PrintStream logingFilePath() throws FileNotFoundException {
		PrintStream log = new PrintStream(
				new FileOutputStream("log/loging.txt"));
		return log;
	}

	// used to generate request body with content type
	public static RequestSpecification requestSpecification(String uri,
			ContentType type, String QueryParamkey, String QueryParamvalue)
			throws FileNotFoundException {
		if (req == null) {
			req = new RequestSpecBuilder()
					.setBaseUri(uri)
					.setContentType(type)
					.addQueryParam(QueryParamkey, QueryParamvalue)
					.addFilter(
							RequestLoggingFilter.logRequestTo(logingFilePath()))
					.addFilter(
							ResponseLoggingFilter
									.logResponseTo(logingFilePath())).build();
			return req;
		}
		return req;

	}

	// used to get response body with content type
	public static ResponseSpecification responseSpecification(int statusCode,
			ContentType type) {
		res = new ResponseSpecBuilder().expectStatusCode(statusCode)
				.expectContentType(type).build();
		return res;
	}
}
