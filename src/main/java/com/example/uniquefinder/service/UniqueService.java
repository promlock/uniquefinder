package com.example.uniquefinder.service;

import com.example.uniquefinder.model.RequestDetailsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

@Service
public class UniqueService {

	private Set<String> uniqueFileSet;


	public ResponseEntity<RequestDetailsResponse> getUniqueFiles(File file) {
		uniqueFileSet = new HashSet<>();
		if (!file.exists() || !file.isDirectory())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDetailsResponse("The given path does not exist or is not a directory."));

		checkUniqueness(file);
		return ResponseEntity.status(HttpStatus.OK).body(new RequestDetailsResponse(file.getPath(), uniqueFileSet));
	}

	private void checkUniqueness(File file) {
		if (file.isDirectory())
			checkContent(file);

		if (Files.isRegularFile(file.toPath()))
			uniqueFileSet.add(file.getName());
	}

	private void checkContent(File file) {
		if (file != null)
			for (File f : file.listFiles()) {
				checkUniqueness(f);
			}
	}

}
