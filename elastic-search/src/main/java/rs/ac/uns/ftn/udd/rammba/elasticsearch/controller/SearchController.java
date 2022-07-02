package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IElasticsearchService;

@RestController
@RequestMapping(value = "/api/search/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

	private IElasticsearchService elasticService;

	@Autowired
	public SearchController(IElasticsearchService elasticService) {
		this.elasticService = elasticService;
	}

	@GetMapping(value = "full-name")
	public ResponseEntity<Object> searchByFullName(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "surname", required = false) String surname) {
		Map<String, String> fields = new HashMap<String, String>();
		if (name != null) {
			fields.put("name", name);
		}
		if (surname != null) {
			fields.put("surname", surname);
		}
		
		Iterable<ApplicantIndexingUnit> results = elasticService.getByFields(fields);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "degree")
	public ResponseEntity<Object> searchByDegree(@RequestParam(name = "degree", required = false) String degree) {
		Map<String, String> fields = new HashMap<String, String>();
		if (degree != null) {
			fields.put("degree", degree);
		}
		
		Iterable<ApplicantIndexingUnit> results = elasticService.getByFields(fields);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "cv")
	public ResponseEntity<Object> searchCvName(@RequestParam(name = "cvContent", required = false) String cvContent) {
		Iterable<ApplicantIndexingUnit> results = elasticService.getByCvContent(cvContent);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
}
