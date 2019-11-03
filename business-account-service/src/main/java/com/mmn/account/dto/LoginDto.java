package com.mmn.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
    private String login;
    private String password;
}
