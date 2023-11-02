package com.spring.boot.security.db.connection.demo.controller;

import com.spring.boot.security.db.connection.demo.exception.UserAlreadyExistsException;
import com.spring.boot.security.db.connection.demo.model.AppUser;
import com.spring.boot.security.db.connection.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<AppUser> createUser(@Valid @RequestBody AppUser user){
            AppUser createdUser = userService.createUser(user);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdUser.getId())
                    .toUri();
            return ResponseEntity.created(location).body(createdUser);
    }
}