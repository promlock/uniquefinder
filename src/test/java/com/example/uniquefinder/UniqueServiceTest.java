package com.example.uniquefinder;

import com.example.uniquefinder.model.RequestDetailsResponse;
import com.example.uniquefinder.repository.RequestDetailsRepository;
import com.example.uniquefinder.service.UniqueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.mockito.Mockito.mock;

public class UniqueServiceTest {

    private UniqueService uniqueService;
    private RequestDetailsRepository requestDetailsRepository;
    @TempDir
    Path tempDir;


    @BeforeEach
    public void initTests() {
        requestDetailsRepository = mock(RequestDetailsRepository.class);
        uniqueService = new UniqueService(requestDetailsRepository);
    }

    @Test
    public void testGetUniqueFiles_WhenDirectoryDoesNotExist_ThenReturnBadRequest() {
        File file = new File(tempDir.toString() + "/notexist");

        ResponseEntity<RequestDetailsResponse> response = uniqueService.getUniqueFiles(file);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getMessage());
        Assertions.assertEquals("The given path does not exist or is not a directory.", response.getBody().getMessage());

        file.deleteOnExit();
    }

    @Test
    public void testGetUniqueFiles_WhenParamIsFile_ThenReturnBadRequest() throws IOException {
        File file = Files.createTempFile("testfile", ".txt").toFile();

        ResponseEntity<RequestDetailsResponse> response = uniqueService.getUniqueFiles(file);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getMessage());
        Assertions.assertEquals("The given path does not exist or is not a directory.", response.getBody().getMessage());

        file.deleteOnExit();
    }

    @Test
    public void testGetUniqueFiles_WhenEmptyDirectoryExist_ThenSuccessAndReturnEmptyResult() {

        ResponseEntity<RequestDetailsResponse> response = uniqueService.getUniqueFiles(tempDir.toFile());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertIterableEquals(response.getBody().getUniqueFileList(), Collections.EMPTY_SET);
    }

    @Test
    public void testGetUniqueFiles_WhenDirectoryExistWithSingleFile_ThenSuccessAndReturnResult() {
        File file = null;
        try {
            file = Files.createTempFile(tempDir, "testfile", ".txt").toFile();
        } catch (IOException e) {
            System.out.println("Error during file creation!");
        }

        ResponseEntity<RequestDetailsResponse> response = uniqueService.getUniqueFiles(tempDir.toFile());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().getUniqueFileList().size(), 1);

        if (file != null)
            file.deleteOnExit();
    }

    @Test
    public void testGetUniqueFiles_WhenDirectoryExistWithMultipleFiles_ThenSuccessAndReturnResult() {
        File file = null;
        File file2 = null;
        try {
            file = Files.createTempFile(tempDir, "testfile", ".txt").toFile();
            file2 = Files.createTempFile(tempDir, "testfile2", ".txt").toFile();
        } catch (IOException e) {
            System.out.println("Error during file creation!");
        }

        ResponseEntity<RequestDetailsResponse> response = uniqueService.getUniqueFiles(tempDir.toFile());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().getUniqueFileList().size(), 2);

        if (file != null)
            file.deleteOnExit();

        if (file2 != null)
            file2.deleteOnExit();
    }

    @Test
    public void testGetUniqueFiles_WhenDirectoryExistWithSubDirectoryWithFileOfDifferentName_ThenSuccessAndReturnResult() {
        File file = null;
        File file2 = null;
        try {
            Path tempDirectory = Files.createTempDirectory(tempDir, "temp");
            file = new File(tempDirectory + "\\file1.txt");
            file.createNewFile();

            file2 = new File(tempDir + "\\file2.txt");
            file2.createNewFile();
        } catch (IOException e) {
            System.out.println("Error during file creation!");
        }

        ResponseEntity<RequestDetailsResponse> response = uniqueService.getUniqueFiles(tempDir.toFile());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().getUniqueFileList().size());

        if (file != null)
            file.deleteOnExit();

        if (file2 != null)
            file2.deleteOnExit();
    }

    @Test
    public void testGetUniqueFiles_WhenDirectoryExistWithSubDirectoryWithFileOfSaneName_ThenSuccessAndReturnResult() {
        File file = null;
        File file2 = null;
        try {
            Path tempDirectory = Files.createTempDirectory(tempDir, "temp");
            file = new File(tempDirectory + "\\file.txt");
            file.createNewFile();

            file2 = new File(tempDir + "\\file.txt");
            file2.createNewFile();
        } catch (IOException e) {
            System.out.println("Error during file creation!");
        }

        ResponseEntity<RequestDetailsResponse> response = uniqueService.getUniqueFiles(tempDir.toFile());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().getUniqueFileList().size());

        if (file != null)
            file.deleteOnExit();

        if (file2 != null)
            file2.deleteOnExit();
    }
}