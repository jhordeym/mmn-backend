package com.mmn.reservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FullLoginDto extends LoginDto {
    @JsonProperty("APIUsername")
    private String APIUsername;
    @JsonProperty("APIPassword")
    private String APIPassword;
}
