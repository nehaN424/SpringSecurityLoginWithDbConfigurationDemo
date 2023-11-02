package com.spring.boot.security.db.connection.demo.service;


import com.spring.boot.security.db.connection.demo.constants.ErrorMessages;
import com.spring.boot.security.db.connection.demo.exception.UserAlreadyExistsException;
import com.spring.boot.security.db.connection.demo.model.AppUser;
import com.spring.boot.security.db.connection.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser createUser(AppUser user) {
            if (userAlreadyExists(user.getUsername())) {
                throw new UserAlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
    }

    private boolean userAlreadyExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}