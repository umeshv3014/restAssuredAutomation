package enumApiResources;

public enum ApiResources {

	AddPlaceAPIResources_JSON("/maps/api/place/add/json"), 
	DeletePlaceAPIResources_JSON("/maps/api/place/delete/json"), 
	GetPlaceAPIResources_JSON("/maps/api/place/nearbysearch/json"), 
	AddPlaceAPIResources_XML("/maps/api/place/add/xml"), 
	DeletePlaceAPIResources_XML("/maps/api/place/delete/xml"), 
	GetPlaceAPIResources_XML("/maps/api/place/nearbysearch/xml");

	private String resources;

	ApiResources(String resources) {
		this.resources = resources;
	}

	public String getApiResources() {
		return resources;
	}

}
