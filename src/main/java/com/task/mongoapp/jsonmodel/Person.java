package com.task.mongoapp.jsonmodel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@FieldNameConstants
@Document("persons")
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {//change String in date to format dd.mm.yyyy

    @Id
    private  String id;

    @JsonProperty("type_of_official")
    private String typeOfOfficial;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("related_persons")
    private List<RelatedPerson> relatedPersonList;

    @JsonProperty("related_companies")
    private  List<RelatedCompanies> relatedCompaniesList;

    @JsonProperty("is_pep")
    private boolean personOfTheYear;

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("full_name_en")
    private String fullNameEn;

    @JsonProperty("first_name_en")
    private String firstNameEn;

    @JsonProperty("last_name_en")
    private String lastNameEn;

    @JsonProperty("url")
    private String url;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("type_of_official_en")
    private String typeOfOfficialEn;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("patronymic_en")
    private String patronymicEn;

    @JsonProperty("died")
    private boolean died;

    @JsonProperty("also_known_as_en")
    private String alsoKnownAsEn;

    @JsonProperty("names")
    private String names;


}
