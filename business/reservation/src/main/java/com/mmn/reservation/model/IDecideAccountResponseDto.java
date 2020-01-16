package com.mmn.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IDecideAccountResponseDto {
    // success
    private Boolean success;
    private Integer userId;
    private String loginUrl;

    // error
    private List<String> errors;
}
