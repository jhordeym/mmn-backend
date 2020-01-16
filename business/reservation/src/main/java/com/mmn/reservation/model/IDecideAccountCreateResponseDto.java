package com.mmn.reservation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.reservation.model.entity.IDecideAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IDecideAccountCreateResponseDto {
    private IDecideAccountResponseDto IDecideResponse;
    private IDecideAccount IDecideSavedAccount;
}
