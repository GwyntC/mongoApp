package com.task.mongoapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.mongoapp.jsonmodel.RelatedPerson;
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
public class PersonFindDto {

    private String dateOfBirth;

    private String fullName;

    private List<RelatedPerson> relatedPersonList;
}
