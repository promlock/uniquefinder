package com.example.uniquefinder.service;

import com.example.uniquefinder.model.RequestDetails;
import com.example.uniquefinder.model.RequestDetailsDTO;
import com.example.uniquefinder.model.RequestDetailsResponse;
import com.example.uniquefinder.repository.RequestDetailsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class for handling unique file search operations and managing request details.
 */
@Service
public class UniqueService {

    /**
     * Set to store unique file names.
     */
    private Set<String> uniqueFileSet;

    /**
     * Repository for storing and retrieving request details.
     */
    private final RequestDetailsRepository detailsRepository;

    /**
     * Constructor to initialize the service with a RequestDetailsRepository.
     *
     * @param detailsRepository The repository for storing and retrieving request details.
     */
    public UniqueService(RequestDetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }


    /**
     * Method to get unique files within a directory and save request details.
     *
     * @param file The directory to search for unique files.
     * @return A ResponseEntity containing the response with unique file details or an error message.
     */
    public ResponseEntity<RequestDetailsResponse> getUniqueFiles(File file) {
        uniqueFileSet = new HashSet<>();

        // Check if the provided file is a valid directory
        if (!file.exists() || !file.isDirectory())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDetailsResponse("The given path does not exist or is not a directory."));

        // Check for uniqueness of files within the directory
        checkUniqueness(file);
        saveRequestDetails(file, uniqueFileSet);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestDetailsResponse(file.getPath(), uniqueFileSet));
    }

    /**
     * Recursive method to check the uniqueness of files within a directory.
     *
     * @param file The directory or file to check for uniqueness.
     */
    private void checkUniqueness(File file) {
        if (file.isDirectory())
            checkContent(file);

        if (Files.isRegularFile(file.toPath()))
            uniqueFileSet.add(file.getName());
    }

    /**
     * Recursive method to check the content of a directory.
     *
     * @param file The directory to check the content of.
     */
    private void checkContent(File file) {
        if (file != null && file.listFiles() != null)
            for (File f : file.listFiles()) {
                checkUniqueness(f);
            }
    }

    /**
     * Method to save request details to the repository.
     *
     * @param file The file or directory for which request details are saved.
     * @param uniqueFileSet A set of unique file names found during the search.
     */
    private void saveRequestDetails(File file, Set<String> uniqueFileSet) {
        detailsRepository.save(new RequestDetails(file.getPath(), System.getProperty("user.name"), uniqueFileSet));
    }

    /**
     * Method to retrieve a paginated history of request details.
     *
     * @param page           The page number for pagination (0-based).
     * @param size           The number of items to display per page.
     * @param sortField      The field by which to sort the results.
     * @param sortDirection  The direction of sorting (e.g., "ASC" or "DESC").
     * @return A Page containing request details in a DTO format.
     */
    public Page<RequestDetailsDTO> getHistory(int page, int size, String sortField, String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        return detailsRepository.findAll(pageRequest)
                .map(t -> new RequestDetailsDTO(
                        t.getFolder(),
                        t.getUserName(),
                        t.getRequestDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        t.getFileNames()));
    }

}
