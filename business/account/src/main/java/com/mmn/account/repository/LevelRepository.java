package com.mmn.account.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmn.account.model.Level;
import com.mmn.account.type.LevelStatus;

public interface LevelRepository extends JpaRepository<Level, String> {

	Optional<Level> findByEmailInvitedAndStatus(final String emailInvited, final LevelStatus status);
}
