package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto.BooleanSearchDto;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IElasticsearchService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeocodingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/search/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

	private IElasticsearchService elasticService;
	private IGeocodingService geocodingService;

	@Autowired
	public SearchController(IElasticsearchService elasticService, IGeocodingService geocodingService) {
		this.elasticService = elasticService;
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
	public ResponseEntity<Object> searchCvName(@RequestParam(name = "cvContent", required = true) String cvContent) {
		Iterable<ApplicantIndexingUnit> results = elasticService.searchByCvContent(cvContent);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping(value = "phrase")
	public ResponseEntity<Object> phraseSearch(@RequestParam(name = "field", required = true) String field,
			@RequestParam(name = "value", required = true) String value) {
		Iterable<ApplicantIndexingUnit> results = elasticService.phraseSearch(field, value);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping(value = "boolean-simple")
	public ResponseEntity<Object> simpleBooleanSearch(@RequestParam(name = "key1", required = true) String key1,
			@RequestParam(name = "value1", required = true) String value1,
			@RequestParam(name = "key2", required = true) String key2,
			@RequestParam(name = "value2", required = true) String value2,
			@RequestParam(name = "isAndOperation", required = true) boolean isAndOperation) {
		ArrayList<BooleanSearchDto> query = new ArrayList<BooleanSearchDto>();
		query.add(new BooleanSearchDto(key1, value1, isAndOperation));
		query.add(new BooleanSearchDto(key2, value2, isAndOperation));
		Iterable<ApplicantIndexingUnit> results = elasticService.advancedBooleanSearch(query);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@PostMapping(value = "boolean-advanced")
	public ResponseEntity<Object> advancedBooleanSearch(@RequestBody List<BooleanSearchDto> body) {
		Iterable<ApplicantIndexingUnit> results = elasticService.advancedBooleanSearch(body);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping(value = "boolean-query")
	public ResponseEntity<Object> booleanSearch(@RequestParam(name = "query", required = true) String query) {
		Iterable<ApplicantIndexingUnit> result = elasticService.advancedBooleanSearch(query);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "geospatial")
	public ResponseEntity<Object> geospatialSearch(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "radius", required = true) double radius) {
		Coordinates coordinates = geocodingService.getCoordinates(city);
		Iterable<ApplicantIndexingUnit> results = elasticService.geospatialSearch(coordinates, radius);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
}
