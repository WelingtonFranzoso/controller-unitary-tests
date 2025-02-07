package com.franzoso.controllers;

import com.franzoso.entities.Occupation;
import com.franzoso.entities.Person;
import com.franzoso.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.lang.String.format;

@RestController
@Slf4j
@RequestMapping(value = "/test-controller", produces = {"application/json"})
@Tag(name = "test-controller")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @Operation(summary = "Upload files!", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File upload successful!"),
            @ApiResponse(responseCode = "422", description = "Invalid request data!"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters!"),
            @ApiResponse(responseCode = "500", description = "Error uploading file!"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadDocument(@RequestPart MultipartFile file) {
        log.info("Upload of file {} started!", file.getOriginalFilename());

        return service.uploadDocument(file);

    }

    @Operation(summary = "Search for data on occupations by age and position held", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful!"),
            @ApiResponse(responseCode = "422", description = "Invalid request data!"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters!"),
            @ApiResponse(responseCode = "500", description = "Error searching for data!"),
    })
    @GetMapping()
    public ResponseEntity<List<Person>> searchByAgeAndOccupation(@RequestParam("Occupation") Occupation occupation,
                                                                 @RequestParam("Age") Integer age) {
        log.info("Searching for professional data by profession = {} and age = {}!", occupation, age);

        return ResponseEntity.ok(service.findPeopleBy(occupation, age));

    }
}
