package com.mmn.account.repository;

import com.mmn.account.model.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
	boolean existsByEmailOrPhone(final String email, final String phone);
	Optional<Account> findByEmail(final String email);
	Optional<Account> findByEmailOrPhone(final String email, final String phone);
	Optional<Account> findByIdAndResetToken(final String id, final String token);
	@Modifying
	@Query("update Account a set a.password = :password where a.id = :id")
	int updatePassword(@Param("password") final String password, @Param("id") final UUID uuid);
}
