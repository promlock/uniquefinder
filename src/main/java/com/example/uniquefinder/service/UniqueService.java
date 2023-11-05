package com.example.uniquefinder.service;

import com.example.uniquefinder.model.RequestDetails;
import com.example.uniquefinder.model.RequestDetailsDTO;
import com.example.uniquefinder.model.RequestDetailsResponse;
import com.example.uniquefinder.repository.RequestDetailsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class UniqueService {

    // Set to store unique file names
    private Set<String> uniqueFileSet;
    private final RequestDetailsRepository detailsRepository;


    public UniqueService(RequestDetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }


    // Method to get unique files within a directory and save request details
    public ResponseEntity<RequestDetailsResponse> getUniqueFiles(File file) {
        uniqueFileSet = new HashSet<>();
        saveRequestDetails(file);

        // Check if the provided file is a valid directory
        if (!file.exists() || !file.isDirectory())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDetailsResponse("The given path does not exist or is not a directory."));

        // Check for uniqueness of files within the directory
        checkUniqueness(file);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestDetailsResponse(file.getPath(), uniqueFileSet));
    }

    // Recursive method to check the uniqueness of files
    private void checkUniqueness(File file) {
        if (file.isDirectory())
            checkContent(file);

        if (Files.isRegularFile(file.toPath()))
            uniqueFileSet.add(file.getName());
    }

    // Recursive method to check the content of a directory
    private void checkContent(File file) {
        if (file != null && file.listFiles() != null)
            for (File f : file.listFiles()) {
                checkUniqueness(f);
            }
    }

    // Method to save request details to the repository
    private void saveRequestDetails(File file) {
        detailsRepository.save(new RequestDetails(file.getPath(), System.getProperty("user.name")));
    }

    // Method to retrieve a paginated history of request details
    public Page<RequestDetailsDTO> getHistory(Pageable pageable) {
        return detailsRepository.findAll(pageable)
                .map(t -> new RequestDetailsDTO(t.getFolder(), t.getUserName(), t.getRequestDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }

}
