package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfStorageService implements IFileStorageService {

	private final String localPath = "/storage/cv/";

	@Override
	public String save(MultipartFile file) throws IllegalArgumentException {
		String extension = getExtension(file);
		if (!extension.equals("pdf")) {
			throw new IllegalArgumentException("Unsupported extension: " + extension);
		}

		String currentPath = Path.of("").toAbsolutePath().getParent().toString();
		String fileName = UUID.randomUUID().toString() + ".pdf";
		File destinationFile = new File(currentPath + localPath + fileName);

		try {
			file.transferTo(destinationFile);
			return fileName;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getExtension(MultipartFile file) {
		String[] parts = file.getOriginalFilename().split("\\.");
		return parts[parts.length - 1];
	}

}
