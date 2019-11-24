package com.mmn.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassRecoveryDto {
    private String id;
    private String token;
}
