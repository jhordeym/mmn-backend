package com.mmn.account.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmn.account.model.entity.Level;
import com.mmn.account.model.type.LevelStatus;

public interface LevelRepository extends JpaRepository<Level, UUID> {

	Optional<Level> findByEmailInvitedAndStatus(final String emailInvited, final LevelStatus status);
}
