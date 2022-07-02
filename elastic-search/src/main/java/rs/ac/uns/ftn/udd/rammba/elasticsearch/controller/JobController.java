package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IElasticsearchService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IFileStorageService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeocodingService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeolocationService;

@RestController
@RequestMapping(value = "/api/job/", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobController {

	private IGeocodingService geocodingService;
	private IGeolocationService geolocationService;
	private IElasticsearchService elasticService;
	private IFileStorageService fileStorageService;

	@Autowired
	public JobController(IGeocodingService geocodingService, IGeolocationService geolocationService,
			IElasticsearchService elasticService, IFileStorageService fileStorageService) {
		this.geocodingService = geocodingService;
		this.geolocationService = geolocationService;
		this.elasticService = elasticService;
		this.fileStorageService = fileStorageService;
	}

	@PostMapping(value = "application")
	public ResponseEntity<Boolean> jobApplication(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "surname", required = true) String surname,
			@RequestParam(name = "mail", required = true) String mail,
			@RequestParam(name = "address", required = true) String address,
			@RequestParam(name = "degree", required = true) String degree,
			@RequestParam(name = "cv", required = true) MultipartFile cv, HttpServletRequest request) {
		try {
			String fileName = fileStorageService.save(cv);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		Coordinates c = geocodingService.getCoordinates(address);
		ApplicantIndexingUnit applicant = new ApplicantIndexingUnit(name, surname, degree, c.latitude, c.longitude);
		indexApplicant(applicant);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	private void indexApplicant(ApplicantIndexingUnit a) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(a);
			boolean works = elasticService.createIndex("temp", json);
			System.out.println(works);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
