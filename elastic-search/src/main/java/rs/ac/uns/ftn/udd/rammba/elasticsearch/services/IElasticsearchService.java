package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

public interface IElasticsearchService {

	boolean createIndex(String indexName, String json);
}
