package com.mmn.account.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mmn.account.type.AccountStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document
@Getter @Setter
public class Account {

	@Id
	private String id;
	private User user;
	private String email;
	private String phoheNumber;
	private String name;
	private String lastName;
	private AccountStatus status = AccountStatus.New;
	private LocalDate accountDate = LocalDate.now();
	private LocalDate statusDate;
	
	public Account confirmated() {
		setStatus(AccountStatus.Authenticated);
		setStatusDate(LocalDate.now());
		return this;
	}
	
}
