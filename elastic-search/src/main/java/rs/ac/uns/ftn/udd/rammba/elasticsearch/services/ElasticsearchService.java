package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.repository.ApplicantRepository;

@SuppressWarnings("deprecation")
@Service
public class ElasticsearchService implements IElasticsearchService {

	private RestHighLevelClient client;
	private final String indexName = ApplicantIndexingUnit.INDEX_NAME;

	private ApplicantRepository applicantRepository;

	@Autowired
	public ElasticsearchService(ApplicantRepository applicantRepository) {
		this.applicantRepository = applicantRepository;
		client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));
	}

	@Override
	public void createIndex(ApplicantIndexingUnit unit) {
		// https://github.com/elastic/elasticsearch-java/issues/74#issuecomment-1020542030
		// https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.8/java-rest-high-document-index.html
		applicantRepository.save(unit);
	}

	@Override
	public Iterable<ApplicantIndexingUnit> searchByFields(Map<String, String> fields) {
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		for (Map.Entry<String, String> entry : fields.entrySet()) {
			queryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
		}
		return search(queryBuilder);
	}

	@Override
	public Iterable<ApplicantIndexingUnit> searchByCvContent(String cvContent) {
		QueryStringQueryBuilder queryBuilder = new QueryStringQueryBuilder(cvContent);
		queryBuilder.defaultField("cvContent");
		queryBuilder.defaultOperator(Operator.AND);
		return search(queryBuilder);
	}

	@Override
	public Iterable<ApplicantIndexingUnit> phraseSearch(String namePhrase, String surnamePhrase, String cvPhrase) {
		MatchPhraseQueryBuilder queryBuilder = new MatchPhraseQueryBuilder("cvContent", cvPhrase);
		return search(queryBuilder);
	}

	@Override
	public Iterable<ApplicantIndexingUnit> simpleBooleanSearch(String key1, String value1, String key2, String value2,
			boolean isAndOperation) {
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		addToBooleanQuery(queryBuilder, key1, value1, isAndOperation);
		addToBooleanQuery(queryBuilder, key2, value2, isAndOperation);
		return search(queryBuilder);
	}
	
	private void addToBooleanQuery(BoolQueryBuilder queryBuilder, String key, String value, boolean isAndOperation) {
		QueryBuilder qb = QueryBuilders.matchQuery(key, value);
		if (key.equals("cvContent")) {
			qb = new QueryStringQueryBuilder(value);
			((QueryStringQueryBuilder) qb).defaultField("cvContent");
			((QueryStringQueryBuilder) qb).defaultOperator(Operator.AND);
		}
		if (isAndOperation) {
			queryBuilder.must(qb);
		} else {
			queryBuilder.should(qb);
		}
	}

	private Iterable<ApplicantIndexingUnit> search(QueryBuilder queryBuilder) {
		SearchRequest searchRequest = new SearchRequest(indexName);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(queryBuilder);
		searchRequest.source(sourceBuilder);
		return getResults(searchRequest);
	}

	private Iterable<ApplicantIndexingUnit> getResults(SearchRequest searchRequest) {
		ArrayList<ApplicantIndexingUnit> results = new ArrayList<ApplicantIndexingUnit>();

		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] searchHits = searchResponse.getHits().getHits();
			ApplicantIndexingUnit applicant;
			for (SearchHit hit : searchHits) {
				Map<String, Object> sourceAsMap = hit.getSourceAsMap();
				String name = (String) sourceAsMap.get("name");
				String surname = (String) sourceAsMap.get("surname");
				String degree = (String) sourceAsMap.get("degree");
				double latitude = (double) sourceAsMap.get("latitude");
				double longitude = (double) sourceAsMap.get("longitude");
				double locationLatitude = (double) sourceAsMap.get("locationLatitude");
				double locationLongitude = (double) sourceAsMap.get("locationLongitude");
				String cvContent = (String) sourceAsMap.get("cvContent");
				applicant = new ApplicantIndexingUnit(name, surname, degree, latitude, longitude, locationLatitude,
						locationLongitude, cvContent);
				results.add(applicant);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return results;
	}

}
