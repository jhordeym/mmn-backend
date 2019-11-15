package com.mmn.account.repository;

import com.mmn.account.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
	Optional<Account> findByEmail(final String email);
	Optional<Account> findByPhone(final String phone);
	Optional<Account> findByEmailOrPhone(final String email, final String phone);
	Optional<Account> findByIdAndResetToken(final String id, final String token);
}
