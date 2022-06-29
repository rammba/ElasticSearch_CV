package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto.GeocodingListDto;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

@Service
public class GeocodingService implements IGeocodingService {

	@Override
	public Coordinates getCoordinates(String address) {
		String json = HttpService.get("https://positionstack.com/geo_api.php?query=" + address);
		if (json == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			GeocodingListDto dto = mapper.readValue(json, GeocodingListDto.class);
			return dto.getCoordinates();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}
}
