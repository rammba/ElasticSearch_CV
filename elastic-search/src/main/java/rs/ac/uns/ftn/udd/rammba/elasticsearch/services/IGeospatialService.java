package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

public interface IGeospatialService {

	double distance(Coordinates first, Coordinates second);
}
