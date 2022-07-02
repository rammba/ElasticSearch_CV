package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

public interface IGeolocationService {

	Coordinates getCoordinates(String ipAddress);
}
