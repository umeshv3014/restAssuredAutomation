package stepdefinations;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import testDataBuild.JSONbodyPayloads;

import com.google.places.api.util.RequestResponseSpecBuilders;
import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import enumApiResources.ApiResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
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
	private Properties prop;
	private JsonPath jp;

	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address)
			throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
		requestSpecification = RequestResponseSpecBuilders
				.requestSpecification(prop.getProperty("qaclickEndPoint"),
						ContentType.JSON, "key",
						prop.getProperty("qaclickApiKey"));
		requestSpecification.given();

		requestSpecification = given().spec(requestSpecification).body(
				JSONbodyPayloads.getPostDataAddPlaceDynamic(name, language,
						address));

	}

	@When("^user calls \"([^\"]*)\" with post http request$")
	public void user_calls_with_post_http_request(String resources) {
		ApiResources addPlaceApiResources = ApiResources.valueOf(resources); //using from enum
		responseSpecification = RequestResponseSpecBuilders
				.responseSpecification(200, ContentType.JSON);
		response = requestSpecification.when()
				.post(addPlaceApiResources.getApiResources()).then()
				.spec(responseSpecification).extract().response();

	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) {
		int status = response.getStatusCode();
		Headers hr = response.getHeaders();
		List<Header> listHR = hr.asList();
		String header = listHR.toString();
		System.out.println(header);
		for (Header object : listHR) {
			// System.out.println("header Name -->" + object.getName()
			// + " header values-->" + object.getValue());
		}
		assertEquals(status, arg1);
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		jp = ApiUtil.rawToJson(response);
		assertEquals(jp.get(keyValue).toString(), Expectedvalue);
	}
}
