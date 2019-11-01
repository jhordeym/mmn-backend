package com.mmn.translation.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class I18NData {
    @Id
    private String id;
    @Indexed
    private String language;
    private String key;
    private String value;
}
