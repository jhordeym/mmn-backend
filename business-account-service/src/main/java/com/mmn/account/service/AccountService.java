package com.mmn.account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mmn.account.dto.AccountMailDto;
import com.mmn.account.model.Account;
import com.mmn.account.repository.AccountRepository;
import com.mmn.account.repository.UserReposiroty;
import com.mmn.mail.Mail;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserReposiroty userReposiroty;
	@Autowired
	private JavaMailSender javaMailSender;

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Account insert(Account account, ServletUriComponentsBuilder servletUriComponentsBuilder) {
		account = accountRepository.save(account);
		account.getUser().setPassword(
				enconde(account.getUser().getPassword())
				);
		userReposiroty.save(account.getUser());
		sendMail(
				new AccountMailDto(
						account, servletUriComponentsBuilder.path("/confirmation/{0}").buildAndExpand(account.getId()).toUri()
						)
				);
		return account;
	}

	private String enconde(String password) {
//		return bCryptPasswordEncoder.encode(password);
		return password;
	}

	public boolean existEmail(String email) {
		return accountRepository.existsByEmail(email);
	}

	public boolean confirmation(String id) {
		Optional<Account> optional = accountRepository.findById(id);
		if (optional.isPresent()) {
			accountRepository.save(
					optional.get().confirmated()
					);
			return true;
		}
		return false;
	}

	public void sendMail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setText(mail.getText());
        message.setTo(mail.getEmail());
        javaMailSender.send(message);
	}

}
