package apiPayLoads;

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

}
