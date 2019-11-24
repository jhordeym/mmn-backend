package com.mmn.account.repository;

import com.mmn.account.model.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
	boolean existsByEmail(String email);
	Optional<Account> findByEmail(final String email);
	Optional<Account> findByPhone(final String phone);
	Optional<Account> findByEmailOrPhone(final String email, final String phone);
	Optional<Account> findByIdAndResetToken(final String id, final String token);
}
