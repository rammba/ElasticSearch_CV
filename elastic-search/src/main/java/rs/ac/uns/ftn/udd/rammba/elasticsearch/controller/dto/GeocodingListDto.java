package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto;

import java.util.List;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

public class GeocodingListDto {

	public List<GeocodingDto> data;

	public GeocodingListDto() {
	}

	public Coordinates getCoordinates() {
		GeocodingDto dto = this.data.get(0);
		return new Coordinates(dto.latitude, dto.longitude);
	}
}
