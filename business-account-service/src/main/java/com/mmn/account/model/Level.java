package com.mmn.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.type.LevelStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Level {
    // Id will be used for the invite of child
    @Id
    String id;
    Account parent;
    Account child;
    LevelStatus status;
}
