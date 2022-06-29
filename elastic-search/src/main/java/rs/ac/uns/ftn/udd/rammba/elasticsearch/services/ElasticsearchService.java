package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

@SuppressWarnings("deprecation")
@Service
public class ElasticsearchService implements IElasticsearchService {

	@Override
	public boolean createIndex(String indexName, String json) {
		// https://github.com/elastic/elasticsearch-java/issues/74#issuecomment-1020542030
		// https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.8/java-rest-high-document-index.html

		RestHighLevelClient restClient = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));

		UUID uuid = UUID.randomUUID();
		IndexRequest request = new IndexRequest(indexName, "doc", uuid.toString());
		request.source(json, XContentType.JSON);

		try {
			restClient.index(request, RequestOptions.DEFAULT);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
