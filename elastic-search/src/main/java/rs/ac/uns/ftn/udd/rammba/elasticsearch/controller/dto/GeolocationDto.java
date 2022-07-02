package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

public class GeolocationDto {

	public String status;
	public String country;
	public String countryCode;
	public String region;
	public String regionName;
	public String city;
	public String zip;
	public double lat;
	public double lon;
	public String timezone;
	public String isp;
	public String org;
	public String as;
	public String query;

	public GeolocationDto() {
	}

	public Coordinates getCoordinates() {
		return new Coordinates(lat, lon);
	}
}
