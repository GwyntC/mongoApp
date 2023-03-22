package com.task.mongoapp.repository;

import com.task.mongoapp.dto.PersonFindDto;
import com.task.mongoapp.dto.TopPerson;
import com.task.mongoapp.jsonmodel.Person;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends MongoRepository<Person,String> {
    @Query(value="{'fullName' : ?0}",fields="{'dateOfBirth': 1, 'fullName': 1, relatedPersonList: 1}")
    Optional<PersonFindDto> findByFullName(String fio);

    @Aggregation(pipeline = {"{$match: {personOfTheYear: true}}","{$group:{_id: $firstName,count: {$sum: 1}}}","{$sort: {count: -1}}", "{$limit:10}"})
    Optional<AggregationResults<TopPerson>> searchPopularPersonNames();
}
