Feature: Validating place API's

Scenario: Verify if place is being succesfully added using AddPlaceAPI
	Given Add Place Payload
	When user calls "AddPlaceAPI" with post http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"