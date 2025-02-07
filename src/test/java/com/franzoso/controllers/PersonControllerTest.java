package com.franzoso.controllers;

import com.franzoso.entities.Occupation;
import com.franzoso.entities.Person;
import com.franzoso.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @InjectMocks
    PersonController controller;

    @Mock
    private PersonService service;

    MockMvc mockMvc;

    private Person person;
    private Integer age;
    private Occupation occupation;
    private MockMultipartFile file;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                        .alwaysDo(print())
                .build();
        file = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

    person = new Person("João", 55, new BigDecimal(10), Occupation.QUALITY_ASSURANCE);
    age = 55;
    occupation = Occupation.QUALITY_ASSURANCE;
    }

    @Test
    void shouldAcceptRequestAndCallServiceToUploadDocumentsSuccessfully() throws Exception {
        when(service.uploadDocument(file)).thenReturn(ResponseEntity.ok("File upload successful!"));

        mockMvc.perform(multipart("/test-controller")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(service).uploadDocument(file);
        verifyNoMoreInteractions(service);

    }


    @Test
    void shouldSearchPersonDataAccordingToAgeAndOccupationSuccessfully() throws Exception {
        when(service.findPeopleBy(occupation, age)).thenReturn(Collections.singletonList(person));

        mockMvc.perform(get("/test-controller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("Occupation", occupation.toString())
                        .param("Age", age.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(service).findPeopleBy(occupation, age);
        verifyNoMoreInteractions(service);

    }

    @Test
    void shouldReturn4xxErrorIfNoMandatoryParametersPassed() throws Exception {

        mockMvc.perform(get("/test-controller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("Age", age.toString()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();

        verifyNoInteractions(service);

    }
}
