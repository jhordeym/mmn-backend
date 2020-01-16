package com.mmn.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IDecideAccountDto {
    private String emailAddress;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String languageCode;
    private Boolean sendWelcomeEmail = true;
}
