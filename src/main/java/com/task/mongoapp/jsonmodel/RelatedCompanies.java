package com.task.mongoapp.jsonmodel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedCompanies {//check data props

    @JsonProperty("relationship_type_en")
    String relationshipTypeEn;

    @JsonProperty("to_company_short_en")
    String toCompanyShortEn;

    @JsonProperty("date_established")
    String dateEstablished;

    @JsonProperty("to_company_edrpou")
    String toCompanyEdrpou;

    @JsonProperty("to_company_founded")
    String toCompanyFounded;

    @JsonProperty("to_company_is_state")
    boolean toCompanyIsState;

    @JsonProperty("date_finished")
    String dateFinished;

    @JsonProperty("share")
    String share;

    @JsonProperty("date_confirmed")
    String dateConfirmed;

    @JsonProperty("to_company_uk")
    String toCompanyUk;

    @JsonProperty("to_company_short_uk")
    String toCompanyShortUk;

    @JsonProperty("to_company_en")
    String toCompanyEn;

    @JsonProperty("relationship_type_uk")
    String relationshipTypeUk;

}
