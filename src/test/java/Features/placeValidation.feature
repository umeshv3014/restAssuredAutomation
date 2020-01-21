Feature: Validating place API's

Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
	Given Add Place Payload "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPIResources_JSON" with post http request
	Then the API call got success with status code 200
	Then "status" in response body is "OK"
	Then "scope" in response body is "APP"

Examples:
			|name	|language|address			|
			|AAHouse|english |world cross center|
			|BBHouse|hindi   |world				|