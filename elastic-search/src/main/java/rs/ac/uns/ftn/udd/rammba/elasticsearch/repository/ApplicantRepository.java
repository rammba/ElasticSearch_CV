package rs.ac.uns.ftn.udd.rammba.elasticsearch.repository;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ApplicantRepository extends ElasticsearchRepository<ApplicantIndexingUnit, String> {

}
