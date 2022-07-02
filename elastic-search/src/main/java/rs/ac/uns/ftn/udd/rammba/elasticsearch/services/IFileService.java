package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

	File save(MultipartFile file) throws IllegalArgumentException;
	String getContent(File file);
}
