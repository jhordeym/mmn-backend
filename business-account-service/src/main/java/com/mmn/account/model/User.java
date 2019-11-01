package com.mmn.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User {

	/*
	 * email
	 */
	@Id
	private String login;
	private String password;
	
}
