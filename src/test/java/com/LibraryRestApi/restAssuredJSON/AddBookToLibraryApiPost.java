package com.LibraryRestApi.restAssuredJSON;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import testDataBuild.JSONbodyPayloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class AddBookToLibraryApiPost {
	
	Properties prop;
	@BeforeTest
	public void readDataFromExternalFiles() throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Test(dataProvider="bookData")
	public void addBookToLibraryAPI(String isbn){
		
		RestAssured.baseURI = prop.getProperty("libraryEndPoint");
		given().log().all().
		header("Content-Type","application/json").
		body(JSONbodyPayloads.getPostDataAddBook(isbn)).
		when().
		post(ApiEndPointResources.getPostResourcesAddBookToLibrary()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().all().
	    body("Msg", equalTo("successfully added"));
	}
	
	@DataProvider(name="bookData")
	public Object[][] getBookData(){
		return new  Object[][] {
				{"343"},{"4333"}
		};
	}
}
