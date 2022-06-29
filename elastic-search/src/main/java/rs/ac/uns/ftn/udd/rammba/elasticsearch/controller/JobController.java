package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto.JobApplicationDto;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IElasticsearchService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeocodingService;

@RestController
@RequestMapping(value = "/api/job/", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobController {

	private IGeocodingService geocodingService;
	private IElasticsearchService elasticService;

	@Autowired
	public JobController(IGeocodingService geocodingService, IElasticsearchService elasticService) {
		this.geocodingService = geocodingService;
		this.elasticService = elasticService;
	}

	@PostMapping(value = "application")
	public ResponseEntity<Boolean> jobApplication(@RequestBody JobApplicationDto dto) {
		Coordinates c = geocodingService.getCoordinates(dto.getAddress());
		indexCoordinates(c);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	private void indexCoordinates(Coordinates c) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(c);
			boolean works = elasticService.createIndex("temp", json);
			System.out.println(works);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
}
