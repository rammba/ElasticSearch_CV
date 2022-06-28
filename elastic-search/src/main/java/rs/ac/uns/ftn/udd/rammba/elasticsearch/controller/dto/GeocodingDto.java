package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto;

import java.util.List;

public class GeocodingDto {
	
	public double latitude;
	public double longitude;
	public String type;
	public String name;
	public Object number;
	public Object postal_code;
	public Object street;
	public int confidence;
	public String region;
	public String region_code;
	public Object county;
	public String locality;
	public String administrative_area;
	public Object neighbourhood;
	public String country;
	public String country_code;
	public String continent;
	public String label;
	public List<Double> bbox_module;
	public Object country_module;
	public Object sun_module;
	public Object timezone_module;

	public GeocodingDto() {
	}
}
