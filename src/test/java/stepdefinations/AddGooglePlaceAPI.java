package stepdefinations;

import java.io.IOException;
import java.util.Properties;

import apiPayLoads.JSONbodyPayloads;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;

public class AddGooglePlaceAPI {
	private RequestSpecification requestSpecification;
	private ResponseSpecification responseSpecification;
	private Response response;
	Properties prop;

	@Given("^Add Place Payload$")
	public void add_Place_Payload() throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);

		requestSpecification = new RequestSpecBuilder()
				.setBaseUri(prop.getProperty("qaclickEndPoint"))
				.addQueryParam("key", prop.getProperty("qaclickApiKey"))
				.setContentType(ContentType.JSON).build();

		requestSpecification = given().spec(requestSpecification).body(
				JSONbodyPayloads.getPostDataAddPlaceDynamic());

	}

	@When("^user calls \"([^\"]*)\" with post http request$")
	public void user_calls_with_post_http_request(String arg1) {
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		response = requestSpecification.when()
				.post(ApiEndPointResources.getPostResourcesAddPlacesJSON())
				.then().spec(responseSpecification).extract().response();

	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) {
		String responseString = response.asString();
		System.out.println(responseString);
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		String resp = response.asString();
		JsonPath jp = new JsonPath(resp);
		assertEquals(jp.get(keyValue).toString(), Expectedvalue);
	}
}
