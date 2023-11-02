package com.spring.boot.security.db.connection.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("users")
@Data
public class AppUser {

    @Id
    private String id;

    @Field("username")
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    private String role;
}
