package com.task.mongoapp.jsonmodel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedPerson {//change String in date to format dd.mm.yyyy

    @JsonProperty("relationship_type_en")
    String relationshipTypeEn;

    @JsonProperty("date_established")
    String dateEstablished;

    @JsonProperty("person_en")
    String personEn;

    @JsonProperty("date_confirmed")
    String dateConfirmed;

    @JsonProperty("is_pep")
    boolean personOfTheYear;

    @JsonProperty("date_finished")
    String dateFinished;

    @JsonProperty("person_uk")
    String personUk;

    @JsonProperty("relationship_type")
    String relationshipType;
}
