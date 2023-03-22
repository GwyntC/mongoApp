package com.task.mongoapp.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class TopPerson {
    @Field("_id")
    String firstName;
    @Field("count")
    String totalCount;
}
