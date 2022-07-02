package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;

@SuppressWarnings("deprecation")
@Service
public class ElasticsearchService implements IElasticsearchService {

	private RestHighLevelClient client;

	public ElasticsearchService() {
		client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));
	}

	@Override
	public boolean createIndex(String indexName, String json) {
		// https://github.com/elastic/elasticsearch-java/issues/74#issuecomment-1020542030
		// https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.8/java-rest-high-document-index.html
		IndexRequest request = new IndexRequest(indexName);
		request.source(json, XContentType.JSON);

		try {
			client.index(request, RequestOptions.DEFAULT);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Iterable<ApplicantIndexingUnit> getByFields(Map<String, String> fields) {
		ArrayList<ApplicantIndexingUnit> results = new ArrayList<ApplicantIndexingUnit>();
		MultiSearchRequest multiSearchRequest = new MultiSearchRequest();
		for (Map.Entry<String, String> entry : fields.entrySet()) {
			SearchRequest searchRequest = new SearchRequest("temp");
			SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
			sourceBuilder.query(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
			searchRequest.source(sourceBuilder);
			multiSearchRequest.add(searchRequest);
		}

		try {
			MultiSearchResponse searchResponse = client.msearch(multiSearchRequest, RequestOptions.DEFAULT);
			MultiSearchResponse.Item[] items = searchResponse.getResponses();
			ApplicantIndexingUnit applicant;
			for (int i = 0; i < items.length; i++) {
				MultiSearchResponse.Item result = items[i];
				SearchHit[] searchHits = result.getResponse().getHits().getHits();
				for (SearchHit hit : searchHits) {
					Map<String, Object> sourceAsMap = hit.getSourceAsMap();
					String applicantName = (String) sourceAsMap.get("name");
					String applicantSurname = (String) sourceAsMap.get("surname");
					String applicantDegree = (String) sourceAsMap.get("degree");
					double latitude = (double) sourceAsMap.get("latitude");
					double longitude = (double) sourceAsMap.get("longitude");
					applicant = new ApplicantIndexingUnit(applicantName, applicantSurname, applicantDegree, latitude,
							longitude);
					results.add(applicant);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

}
