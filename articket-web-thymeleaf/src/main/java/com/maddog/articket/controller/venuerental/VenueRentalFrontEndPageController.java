package com.maddog.articket.controller.venuerental;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class VenueRentalFrontEndPageController {

	private static final String PDF_PATH = "static/documents/pdf/";

	@GetMapping("/venue-introduction")
	public String venueIntroduction(Model model) {
		// 如果需要，可以在這裡添加模型屬性
		return "venueIntroduction";
	}

	@GetMapping("/venueRental/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
		Path filePath = Paths.get("src/main/resources/static/documents/pdf").resolve(fileName);
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists() || resource.isReadable()) {
			String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replaceAll("\\+",
					"%20");
			String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"; filename*=UTF-8''"
					+ encodedFileName;

			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
		} else {
			throw new RuntimeException("Could not read the file!");
		}
	}
}