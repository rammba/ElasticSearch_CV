package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IElasticsearchService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeocodingService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeospatialService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/search/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

	private IElasticsearchService elasticService;
	private IGeospatialService geospatialService;
	private IGeocodingService geocodingService;

	@Autowired
	public SearchController(IElasticsearchService elasticService, IGeospatialService geospatialService,
			IGeocodingService geocodingService) {
		this.elasticService = elasticService;
		this.geospatialService = geospatialService;
		this.geocodingService = geocodingService;
	}

	@GetMapping(value = "full-name")
	public ResponseEntity<Object> searchByFullName(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "surname", required = false) String surname) {
		Map<String, String> fields = new HashMap<String, String>();
		if (name != null && !name.isBlank()) {
			fields.put("name", name);
		}
		if (surname != null && !surname.isBlank()) {
			fields.put("surname", surname);
		}

		Iterable<ApplicantIndexingUnit> results = elasticService.searchByFields(fields);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping(value = "degree")
	public ResponseEntity<Object> searchByDegree(@RequestParam(name = "degree", required = false) String degree) {
		Map<String, String> fields = new HashMap<String, String>();
		if (degree != null && !degree.isBlank()) {
			fields.put("degree", degree);
		}

		Iterable<ApplicantIndexingUnit> results = elasticService.searchByFields(fields);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping(value = "cv")
	public ResponseEntity<Object> searchCvName(@RequestParam(name = "cvContent", required = false) String cvContent) {
		Iterable<ApplicantIndexingUnit> results = elasticService.searchByCvContent(cvContent);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping(value = "phrase")
	public ResponseEntity<Object> phraseSearch(@RequestParam(name = "namePhrase", required = false) String namePhrase,
			@RequestParam(name = "surnamePhrase", required = false) String surnamePhrase,
			@RequestParam(name = "cvPhrase", required = false) String cvPhrase) {
		Iterable<ApplicantIndexingUnit> results = elasticService.phraseSearch(namePhrase, surnamePhrase, cvPhrase);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping(value = "geospatial")
	public ResponseEntity<Object> geospatialSearch(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "radius", required = true) double radius) {
		Coordinates c1 = geocodingService.getCoordinates("Novi Sad");
		Coordinates c2 = geocodingService.getCoordinates("Beograd");
		double x = geospatialService.distance(c1, c2);
		return new ResponseEntity<>(x, HttpStatus.OK);
	}
}
