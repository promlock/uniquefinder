package com.example.uniquefinder;

import com.example.uniquefinder.model.RequestDetailsResponse;
import com.example.uniquefinder.service.UniqueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;

public class UniqueServiceTest {

    private UniqueService uniqueService;


    @BeforeEach
    public void initTests() {
        uniqueService = new UniqueService();
    }

    @Test
    public void testGetUniqueFiles_WhenFileDoesNotExist_ThenReturnBadRequest() {
        File file = new File("dummyPath/file");

        ResponseEntity<RequestDetailsResponse> response = uniqueService.getUniqueFiles(file);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getMessage());
        Assertions.assertEquals("The given path does not exist or is not a directory.", response.getBody().getMessage());
    }
}