package com.mmn.account.dto;

import java.net.URI;

import com.mmn.account.model.Account;
import com.mmn.mail.Mail;

public class AccountMailDto implements Mail {

	private Account account;
	private String text;
	
	public AccountMailDto(Account account, URI uri) {
		this.account = account;
		this.text = uri.toString();
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getEmail() {
		return account.getEmail();
	}

}
