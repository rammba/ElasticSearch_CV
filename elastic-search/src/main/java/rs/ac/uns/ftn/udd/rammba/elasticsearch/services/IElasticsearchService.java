package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.util.Map;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;

public interface IElasticsearchService {

	void createIndex(ApplicantIndexingUnit unit);
	Iterable<ApplicantIndexingUnit> searchByFields(Map<String, String> fields);
	Iterable<ApplicantIndexingUnit> searchByCvContent(String cvContent);
	Iterable<ApplicantIndexingUnit> phraseSearch(String namePhrase, String surnamePhrase, String cvPhrase);
}
