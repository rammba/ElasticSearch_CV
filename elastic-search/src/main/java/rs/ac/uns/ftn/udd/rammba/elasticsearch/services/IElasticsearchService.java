package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto.BooleanSearchDto;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

public interface IElasticsearchService {

	void createIndex(ApplicantIndexingUnit unit);

	Iterable<ApplicantIndexingUnit> searchByFields(Map<String, String> fields);

	Iterable<ApplicantIndexingUnit> searchByCvContent(String cvContent);

	Iterable<ApplicantIndexingUnit> phraseSearch(String field, String value);

	Iterable<ApplicantIndexingUnit> simpleBooleanSearch(ArrayList<String> keys, ArrayList<String> values,
			boolean isAndOperation);
	
	Iterable<ApplicantIndexingUnit> advancedBooleanSearch(List<BooleanSearchDto> request);

	Iterable<ApplicantIndexingUnit> geospatialSearch(Coordinates coordinates, double radius);
}
