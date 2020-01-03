package com.google.places.resoureces;

public class ApiEndPointResources {

	public static String getResourcesGooglePlace() {
		String googlePlace = "/maps/api/place/nearbysearch/json";
		return googlePlace;
	}

	public static String getPostResourcesAddPlaces() {
		String googlePlace = "/maps/api/place/add/json";
		return googlePlace;
	}

	public static String getPostResourcesDeletePlaces() {
		String googlePlace = "/maps/api/place/delete/json";
		return googlePlace;
	}
}
