package com.google.places.resoureces;

public class ApiEndPointResources {

	public static String getResourcesGooglePlaceJSON() {
		String googlePlace = "/maps/api/place/nearbysearch/json";
		return googlePlace;
	}

	public static String getResourcesGooglePlaceXML() {
		String googlePlace = "/maps/api/place/nearbysearch/xml";
		return googlePlace;
	}

	public static String getPostResourcesAddPlacesJSON() {
		String googlePlace = "/maps/api/place/add/json";
		return googlePlace;
	}

	public static String GetPostResourcesAddPlacesXML() {
		String googlePlace = "/maps/api/place/add/xml";
		return googlePlace;
	}

	public static String getPostResourcesDeletePlacesJSON() {
		String googlePlace = "/maps/api/place/delete/json";
		return googlePlace;
	}

	public static String getPostResourcesDeletePlacesXML() {
		String googlePlace = "/maps/api/place/delete/xml";
		return googlePlace;
	}
	
	public static String getPostResourcesAddBookToLibrary() {
		String addBook = "/Library/Addbook.php";
		return addBook;
	}
	
	
}
