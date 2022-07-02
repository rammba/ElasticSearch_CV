package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfFileService implements IFileService {

	private final String localPath = "/storage/cv/";

	@Override
	public File save(MultipartFile file) throws IllegalArgumentException {
		String extension = getExtension(file);
		if (!extension.equals("pdf")) {
			throw new IllegalArgumentException("Unsupported extension: " + extension);
		}

		String currentPath = Path.of("").toAbsolutePath().getParent().toString();
		String fileName = UUID.randomUUID().toString() + ".pdf";
		String destinationPath = currentPath + localPath + fileName;
		File destinationFile = new File(destinationPath);

		try {
			file.transferTo(destinationFile);
			return destinationFile;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getExtension(MultipartFile file) {
		String[] parts = file.getOriginalFilename().split("\\.");
		return parts[parts.length - 1];
	}

	@Override
	public String getContent(File file) {
		PDDocument doc;
		try {
			doc = PDDocument.load(file);
			String content = new PDFTextStripper().getText(doc);
			doc.close();
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

}
