package com.example.uniquefinder.controller;

import com.example.uniquefinder.model.RequestDetailsDTO;
import com.example.uniquefinder.model.RequestDetailsResponse;
import com.example.uniquefinder.service.UniqueService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Controller class for handling unique file search operations and retrieving search history details.
 */
@RestController
public class UniqueController {

    /**
     * Service for performing unique file search and managing request details.
     */
    private final UniqueService uniqueService;

    /**
     * Constructor to initialize the controller with a UniqueService instance.
     *
     * @param uniqueService The service responsible for performing unique file search and managing request details.
     */
    public UniqueController(UniqueService uniqueService) {
        this.uniqueService = uniqueService;
    }

    /**
     * Endpoint for searching unique files within the specified directory.
     *
     * @param path The path to the directory where unique files should be searched.
     * @return A ResponseEntity containing the response with unique file details or an error message.
     */
    @Operation(summary = "Endpoint for searching unique files within the specified directory.")
    @GetMapping("/unique-files")
    public ResponseEntity<RequestDetailsResponse> getUniqueFiles(@RequestParam @Nonnull String path) {
        return uniqueService.getUniqueFiles(new File(path));
    }

    /**
     * Endpoint for retrieving the history of previous unique file search requests.
     *
     * @param page           The page number for pagination (0-based).
     * @param size           The number of items to display per page.
     * @param sortField      The field by which to sort the results.
     * @param sortDirection  The direction of sorting (e.g., "ASC" or "DESC").
     * @return A Page containing request details in DTO format.
     */
    @Operation(summary = "Endpoint for retrieving the history of previous unique file search requests.")
    @GetMapping("/history")
    public Page<RequestDetailsDTO> getHistory(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortField", defaultValue = "requestDate") String sortField,
            @RequestParam(name = "sortDirection", defaultValue = "desc") String sortDirection)
    {
        return uniqueService.getHistory(page, size, sortField, sortDirection);
    }
}
