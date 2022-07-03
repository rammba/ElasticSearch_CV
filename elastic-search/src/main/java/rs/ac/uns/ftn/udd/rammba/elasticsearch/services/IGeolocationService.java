package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import javax.servlet.http.HttpServletRequest;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

public interface IGeolocationService {

	String getClientIp(HttpServletRequest request);

	Coordinates getCoordinates(String ipAddress);
}
