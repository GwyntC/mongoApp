package com.task.mongoapp.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.mongoapp.jsonmodel.RelatedCompanies;
import com.task.mongoapp.jsonmodel.RelatedPerson;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;



public class PersonData {
    @Id
    private  int id;

    private String typeOfOfficial;

    private String firstName;

    private String lastName;

    private List<RelatedPerson> relatedPersonList;

    private  List<RelatedCompanies> relatedCompaniesList;

    private boolean personOfTheYear;

    private String photo;

    private String fullNameEn;

    private String firstNameEn;

    private String lastNameEn;

    private String url;

    private String dateOfBirth;

    private String typeOfOfficialEn;

    private String fullName;

    private String patronymic;

    private String patronymicEn;

    private boolean died;

    private String alsoKnownAsEn;

    private String names;
}
