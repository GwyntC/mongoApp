package com.task.mongoapp.service;

import com.task.mongoapp.dto.PersonFindDto;
import com.task.mongoapp.dto.TopPerson;
import com.task.mongoapp.exceptions.NotFoundException;
import com.task.mongoapp.jsonmodel.Person;
import com.task.mongoapp.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;
    @Override
    public String createPeople(List<Person> personList) {
        peopleRepository.saveAll(personList);
        return "200";
    }

    @Override
    public PersonFindDto findByFIO(String fullName) {
        return getOrThrow(fullName);
    }

    @Override
    public AggregationResults<TopPerson> getPersons() {
        return getOrThrow();
    }
    private PersonFindDto getOrThrow(String fullName){
        return peopleRepository.findByFullName(fullName)
                .orElseThrow(()-> new NotFoundException("Person with that fullName is not found".formatted(fullName)));
    }
    private AggregationResults<TopPerson> getOrThrow(){
        return peopleRepository.searchPopularPersonNames()
                .orElseThrow(()->new NotFoundException("This database has no popular persons"));
    }
   // private static voi
}
