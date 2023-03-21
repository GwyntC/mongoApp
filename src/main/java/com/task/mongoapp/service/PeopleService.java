package com.task.mongoapp.service;

import com.task.mongoapp.dto.PersonFindDto;
import com.task.mongoapp.jsonmodel.Person;

import java.util.List;

public interface PeopleService {
 String createPeople(List<Person> personList);
PersonFindDto findByFIO(String fullName);
}
