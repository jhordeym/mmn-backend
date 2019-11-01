package com.mmn.account.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mmn.account.model.User;

public interface UserReposiroty extends MongoRepository<User, String>{

}
