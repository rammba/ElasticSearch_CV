package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

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

	Iterable<ApplicantIndexingUnit> advancedBooleanSearch(List<BooleanSearchDto> request);

	Iterable<ApplicantIndexingUnit> geospatialSearch(Coordinates coordinates, double radius);
	
	Iterable<ApplicantIndexingUnit> advancedBooleanSearch(String query);
}
