package com.task.mongoapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.task.mongoapp.MongoAppApplication;
import com.task.mongoapp.TestUtil;
import com.task.mongoapp.dto.PersonFindDto;
import com.task.mongoapp.dto.TopPerson;
import com.task.mongoapp.jsonmodel.Person;
import com.task.mongoapp.jsonmodel.RelatedPerson;
import com.task.mongoapp.repository.PeopleRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MongoAppApplication.class)
@AutoConfigureMockMvc
public class PeopleControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    PeopleRepository repository;

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

    @Test
    void testSavePeople_success() throws Exception {
        File file =new File("src/test/java/resources/pep.zip");
        FileInputStream input=new FileInputStream(file);
        MockMultipartFile multipartFile=new MockMultipartFile("zip",file.getName(),"application/zip",new FileInputStream("src/test/java/resources/pep.zip"));
        mvc.perform(MockMvcRequestBuilders.multipart("/api/people/create")
                .file(multipartFile))
                .andExpect(status().is(201));
    }
    @Test
    void findPeople_success() throws Exception {
        Person person=new Person();
        person.setFullName("Taras Kachan");
        person.setDateOfBirth("17.09.2031");
        person.setRelatedPersonList(new ArrayList<RelatedPerson>());
      repository.save(person);
        MockHttpServletResponse response = mvc.perform(get("/api/people/get/"+"Taras Kachan")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();
        PersonFindDto result = TestUtil.parseJson(response.getContentAsString(), new TypeReference<>() {});
        assertThat(result.getFullName()).isEqualTo("Taras Kachan");
        assertThat(result.getDateOfBirth()).isEqualTo("17.09.2031");
    }
    @Test
    void findFamous_success() throws Exception {
        Person person=new Person();
        person.setFirstName("OMD");
        person.setPersonOfTheYear(true);
        repository.save(person);
        Person person1 =new Person();
        person1.setPersonOfTheYear(true);
        person1.setFirstName("OMD");
        repository.save(person1);
        MockHttpServletResponse response = mvc.perform(post("/api/people/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();
        List<TopPerson> topPersonList = TestUtil.parseJson(response.getContentAsString(), new TypeReference<>() {});
        assertThat(topPersonList.get(0).getFirstName()).isEqualTo("OMD");
        assertThat(topPersonList.get(0).getTotalCount()).isEqualTo("2");
    }

}
