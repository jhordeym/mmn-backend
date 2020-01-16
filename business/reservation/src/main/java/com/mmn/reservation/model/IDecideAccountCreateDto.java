package com.mmn.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IDecideAccountCreateDto {
    private IDecideAccountDto idecideAccount;
    private String travinedAccountId;
}
