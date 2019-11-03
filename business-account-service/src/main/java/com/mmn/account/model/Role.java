package com.mmn.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {
    @Id
    private Integer id;
    private String name;
}
