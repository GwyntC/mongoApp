package com.task.mongoapp;

import com.task.mongoapp.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MongoAppApplication.class)
@AutoConfigureMockMvc
public class PeopleControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    PeopleRepository repository;

}
