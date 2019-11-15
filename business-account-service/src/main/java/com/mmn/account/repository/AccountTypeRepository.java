package com.mmn.account.repository;

import com.mmn.account.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<Role, String> {
}
