package stepdefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Properties;

import apiPayLoads.JSONbodyPayloads;

import com.google.places.api.util.RequestResponseSpecBuilders;
import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.ApiEndPointResources;
import com.google.places.resoureces.Constant;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddGooglePlaceAPI {
	Properties prop;
	RequestSpecification req;
	ResponseSpecification res;

	public void readDataFromExternalFiles() throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}

	@Given("^Add Place Payload$")
	public void add_Place_Payload() {
		 req = RequestResponseSpecBuilders.reqSpecBuilder(
				prop.getProperty("qaclickEndPoint"), ContentType.JSON);
		 res = RequestResponseSpecBuilders
				.respSpecBuilder(200, ContentType.JSON);
		given().spec(req).body(JSONbodyPayloads.getPostDataAddPlace()).when()
				.post(ApiEndPointResources.getPostResourcesAddPlacesJSON())
				.then().spec(res).and().body("status", equalTo("OK"));

	}

	@When("^user calls \"([^\"]*)\" with post http request$")
	public void user_calls_with_post_http_request(String arg1) {

	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) {

	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String arg1, String arg2) {

	}
}
