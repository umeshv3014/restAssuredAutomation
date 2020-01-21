package testDataBuild;

import java.util.ArrayList;
import java.util.List;

import apiPayLoads.AddGooglePlaces;
import apiPayLoads.LocationGooglePlaces;

public class JSONbodyPayloads {

	public static String getPostDataAddPlace() {
		String addPlacePlayload = "{"
				+ "\"location\": {"
				+ "\"lat\": -33.8669710,"
				+ "\"lng\": 151.1958750"
				+ "},"
				+ "\"accuracy\": 50,"
				+ "\"name\": \"Google Shoes!\","
				+ "\"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","
				+ "\"types\": [\"shoe_store\"],"
				+ "\"website\": \"http://www.google.com.au/\","
				+ "\"language\": \"en-AU\"" + "}";

		return addPlacePlayload;
	}

	public static String getPostDataDeletePlace(String placeId) {
		String deletePlace = "{" + "\"place_id\":\"" + placeId + "\"" + "}";
		return deletePlace;
	}

	public static String getPostDataAddBook(String isbn) {
		String deletePlace = "{\r\n \r\n\"name\":\"Learn Appium Automation with Java\",\r\n\"isbn\":\"bcd\",\r\n\"aisle\":\""
				+ isbn + "\",\r\n\"author\":\"John foe\"\r\n}\r\n";
		return deletePlace;
	}

	public static AddGooglePlaces getPostDataAddPlaceDynamic(String name,
			String language, String address) {
		AddGooglePlaces ap = new AddGooglePlaces();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		LocationGooglePlaces lp = new LocationGooglePlaces();
		lp.setLat(-38.383494);
		lp.setLng(33.427362);
		ap.setLocation(lp);
		List<String> arr = new ArrayList<String>();
		arr.add("shoe park");
		arr.add("shop");
		ap.setTypes(arr);
		return ap;
	}

}
