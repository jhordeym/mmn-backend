package com.mmn.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mmn.account.model.Account;
import com.mmn.account.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Account account) {
		if (accountService.existEmail(account.getEmail())) {
			return new ResponseEntity<Account>(HttpStatus.CONFLICT);
		} else {
			accountService.insert(account, ServletUriComponentsBuilder.fromCurrentRequest());
		}
		return new ResponseEntity<Account>(account, HttpStatus.CREATED);
	}
	
	@PostMapping("/confirmation/{id}")
	public ResponseEntity<?> emailConfirmation(@PathVariable("id") String id) {
		if (accountService.confirmation(id)) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
