package com.task.mongoapp.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.task.mongoapp.dto.PersonFindDto;
import com.task.mongoapp.dto.RestResponse;
import com.task.mongoapp.dto.TopPerson;
import com.task.mongoapp.jsonmodel.Person;
import com.task.mongoapp.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/people/")
@RequiredArgsConstructor
public class PersonController {

    private final PeopleService peopleService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse savePeople(@RequestPart("zip") MultipartFile file) throws IOException {
        MongoDatabase database;
        try (MongoClient mongoClient = MongoClients.create()) {
            database = mongoClient.getDatabase("test");
            database.getCollection("persons").drop();
        }
        List<Person> personList = ZipJsonReader.readZip(file);

        peopleService.createPeople(personList);
        return new RestResponse("OK");
    }

    @GetMapping("get/{fullname}")
    public PersonFindDto getPeople(@PathVariable String fullname) {
        return peopleService.findByFIO(fullname);
    }

    @PostMapping("_search")
    public List<TopPerson> getPOY() {
        return peopleService.getPersons().getMappedResults();
    }
}
