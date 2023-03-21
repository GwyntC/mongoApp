package com.task.mongoapp.service;

import com.task.mongoapp.dto.PersonFindDto;
import com.task.mongoapp.jsonmodel.Person;
import com.task.mongoapp.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
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
        return peopleRepository.findByFullName(fullName).get();
    }
}
