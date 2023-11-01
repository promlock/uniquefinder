package com.example.uniquefinder.controller;

import com.example.uniquefinder.model.RequestDetailsResponse;
import com.example.uniquefinder.service.UniqueService;
import jakarta.annotation.Nonnull;
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

    @GetMapping("/unique-files")
    public ResponseEntity<RequestDetailsResponse> getUniqueFiles(@RequestParam @Nonnull String path) {
        return uniqueService.getUniqueFiles(new File(path));
    }

}
