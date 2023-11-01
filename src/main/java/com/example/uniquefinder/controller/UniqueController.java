package com.example.uniquefinder.controller;

import com.example.uniquefinder.model.RequestDetailsDTO;
import com.example.uniquefinder.model.RequestDetailsResponse;
import com.example.uniquefinder.service.UniqueService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class UniqueController {

    private final UniqueService uniqueService;

    public UniqueController(UniqueService uniqueService) {
        this.uniqueService = uniqueService;
    }

    @Operation(summary = "Egyedi fájlok keresése a megadott könyvtárban.")
    @GetMapping("/unique-files")
    public ResponseEntity<RequestDetailsResponse> getUniqueFiles(@RequestParam @Nonnull String path) {
        return uniqueService.getUniqueFiles(new File(path));
    }

    @Operation(summary = "Korábbi keresések részleteinek lekérése.")
    @GetMapping("/history")
    public Page<RequestDetailsDTO> getHistory(Pageable pageable) {
        return uniqueService.getHistory(pageable);
    }
}
