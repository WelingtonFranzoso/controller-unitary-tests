package com.franzoso.services;

import com.franzoso.entities.Occupation;
import com.franzoso.entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

@Service
@Slf4j
public class PersonService {

    @Value("${path.documents}")
    private String path;

    public ResponseEntity<String> uploadDocument(MultipartFile file) {
        try{
            notNull(file, "File is required!");

            String rootFile = path + "/" + file.getOriginalFilename();
            File newDocument = new File(rootFile);
            FileOutputStream fileOutputStream = new FileOutputStream(newDocument, true);

            file.getInputStream().transferTo(fileOutputStream);
            return ResponseEntity.ok("Uploaded file! " + file.getName());
        } catch (Exception e) {
            throw new RuntimeException("Error loading file!");
        }
    }

    public List<Person> findPeopleBy(Occupation occupation, Integer age) {
        List<Person> personList = findPeople();

        return personList.stream()
                .filter(Objects::nonNull)
                .filter(person -> person.occupation().equals(occupation))
                .filter(person -> Objects.equals(person.age(), age))
                .toList();
    }
    private static List<Person> findPeople() {

        return Arrays.asList(
                new Person("Angelica", 30, BigDecimal.TEN, Occupation.DEVELOPER),
                new Person("Maria", 37, BigDecimal.TEN, Occupation.DEVELOPER),
                new Person("Pedro", 40, BigDecimal.ONE, Occupation.SCRUM_MASTER),
                new Person("Felipe", 28, BigDecimal.ONE, Occupation.SCRUM_MASTER),
                new Person("Joao", 43, BigDecimal.ONE, Occupation.PRODUCT_OWNER),
                new Person("Elder", 46, BigDecimal.ONE, Occupation.PRODUCT_OWNER)
        );
    }
}
