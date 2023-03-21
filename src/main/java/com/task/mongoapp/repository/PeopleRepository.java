package com.task.mongoapp.repository;

import com.task.mongoapp.dto.PersonFindDto;
import com.task.mongoapp.jsonmodel.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends MongoRepository<Person,String> {
    @Query(value="{'fullName' : ?0}",fields="{'dateOfBirth': 1, 'fullName': 1, relatedPersonList: 1}")
    Optional<PersonFindDto> findByFullName(String fio);
}
