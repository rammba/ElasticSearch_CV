package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto.GeolocationDto;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

@Service
public class GeolocationService implements IGeolocationService {

	@Override
	public Coordinates getCoordinates(String ipAddress) {
		String json = HttpService.get("http://ip-api.com/json/" + ipAddress);
		if (json == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			GeolocationDto dto = mapper.readValue(json, GeolocationDto.class);
			return dto.getCoordinates();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
