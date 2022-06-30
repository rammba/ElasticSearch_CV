package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {

	String save(MultipartFile file) throws IllegalArgumentException;
}
