package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.util.Map;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;

public interface IElasticsearchService {

	boolean createIndex(String indexName, String json);
	Iterable<ApplicantIndexingUnit> getByFields(Map<String, String> fields);
}
