package com.mmn.account.model.dto;

import com.mmn.account.model.entity.Account;
import lombok.Data;

@Data
public class AccountLinkDto {
    private Account account; // new account;
    private String link; // to use mail;
}
