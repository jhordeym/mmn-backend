package com.mmn.account.dto;

import com.mmn.account.model.Account;

import lombok.Data;

@Data
public class InviteDto {

	private Account account; //no convite será a conta pai/ confirmação será a conta filho
	private String emailInvited;//email
	
	private String id; //id será usado na confirmacão
	private String link;
}
