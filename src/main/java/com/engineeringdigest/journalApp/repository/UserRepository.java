package com.engineeringdigest.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.engineeringdigest.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username); // it will find field in entity which matches exactly to username(findByUsername)

    void deleteByUsername(String username);
}
