package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.ApplicantIndexingUnit;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.repository.ApplicantRepository;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IElasticsearchService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IFileService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeocodingService;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.services.IGeolocationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/job/", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobController {

	private IGeocodingService geocodingService;
	private IGeolocationService geolocationService;
	private IElasticsearchService elasticService;
	private IFileService fileService;

	@Autowired
	public JobController(IGeocodingService geocodingService, IGeolocationService geolocationService,
			IElasticsearchService elasticService, IFileService fileService) {
		this.geocodingService = geocodingService;
		this.geolocationService = geolocationService;
		this.elasticService = elasticService;
		this.fileService = fileService;
	}

	@PostMapping(value = "application")
	public ResponseEntity<Boolean> jobApplication(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "surname", required = true) String surname,
			@RequestParam(name = "mail", required = true) String mail,
			@RequestParam(name = "address", required = true) String address,
			@RequestParam(name = "degree", required = true) String degree,
			@RequestParam(name = "cv", required = true) MultipartFile cv, HttpServletRequest request) {
		File savedCv;
		try {
			savedCv = fileService.save(cv);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		String cvContent = fileService.getContent(savedCv);

		Coordinates addressCoordinates = geocodingService.getCoordinates(address);
		String ip = geolocationService.getClientIp(request);
		Coordinates ipCoordinates = geolocationService.getCoordinates(ip);
		ApplicantIndexingUnit applicant = new ApplicantIndexingUnit(name, surname, degree, addressCoordinates.latitude,
				addressCoordinates.longitude, ipCoordinates.latitude, ipCoordinates.longitude, cvContent);
		elasticService.createIndex(applicant);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
