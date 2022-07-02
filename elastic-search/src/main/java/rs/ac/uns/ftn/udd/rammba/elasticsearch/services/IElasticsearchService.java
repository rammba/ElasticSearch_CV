package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.util.Map;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;

public interface IElasticsearchService {

	boolean createIndex(String json);
	Iterable<ApplicantIndexingUnit> getByFields(Map<String, String> fields);
	Iterable<ApplicantIndexingUnit> getByCvContent(String cvContent);
	Iterable<ApplicantIndexingUnit> phraseSearch(String namePhrase, String surnamePhrase, String cvPhrase);
}
