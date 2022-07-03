package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

@Service
public class GeospatialService implements IGeospatialService {

	private static double earthRadius = 6371;

	@Override
	public double distance(Coordinates first, Coordinates second) {
		double lat1 = first.latitude;
		double lon1 = first.longitude;
		double lat2 = second.latitude;
		double lon2 = second.longitude;

		double latitudeDistance = toRadians(lat2 - lat1);
		double longitudeDistance = toRadians(lon2 - lon1);

		double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2) + Math.cos(toRadians(lat1))
				* Math.cos(toRadians(lat2)) * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return earthRadius * c;
	}

	private static Double toRadians(double value) {
		return Math.toRadians(value);
	}

}
