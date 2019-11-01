package com.mmn.translation.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class I18NDto {
    private String locale;
    private Map<String, String> dictionary;
}
