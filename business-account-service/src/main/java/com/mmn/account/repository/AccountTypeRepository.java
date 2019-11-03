package com.mmn.account.repository;

import com.mmn.account.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountTypeRepository extends MongoRepository<Role, String> {
}
