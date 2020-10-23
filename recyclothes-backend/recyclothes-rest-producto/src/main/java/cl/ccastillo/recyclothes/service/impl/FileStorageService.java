package cl.ccastillo.recyclothes.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cl.ccastillo.recyclothes.exception.FileStorageException;
import cl.ccastillo.recyclothes.exception.MyFileNotFoundException;
import cl.ccastillo.recyclothes.property.FileStorageProperties;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation;

	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getPendiente()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..") || !fileName.endsWith(".jpg")   ) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			fileName = String.format("%s.%s", UUID.randomUUID().toString(), "jpg");

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("copia al "+targetLocation);
			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
	
	// @TODO 
	// https://howtodoinjava.com/spring-boot2/read-file-from-resources/ 
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				 resource = resourceLoader.getResource("classpath:noimage.png");
				 return resource;
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
}
