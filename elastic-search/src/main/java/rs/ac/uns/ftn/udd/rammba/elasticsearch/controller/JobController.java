package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto.JobApplicationDto;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeocodingService;

@RestController
@RequestMapping(value = "/api/job/", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobController {

	private IGeocodingService geocodingService;

	@Autowired
	public JobController(IGeocodingService geocodingService) {
		this.geocodingService = geocodingService;
	}

	@PostMapping(value = "application")
	public ResponseEntity<Boolean> jobApplication(@RequestBody JobApplicationDto dto) {
		Coordinates c = geocodingService.getCoordinates(dto.getAddress());
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
