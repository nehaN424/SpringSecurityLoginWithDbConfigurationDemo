package com.spring.boot.security.db.connection.demo.repository;


import com.spring.boot.security.db.connection.demo.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<AppUser, Object> {

    public AppUser findByUsername(String username);
}
