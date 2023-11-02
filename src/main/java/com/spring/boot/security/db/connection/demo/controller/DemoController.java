package com.spring.boot.security.db.connection.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/balance")
    public String balance(){
        return "balance";
    }

    @GetMapping("/card")
    public String card(){
        return "card details";
    }

    @GetMapping("/accounts")
    public String accounts(){
        return "All accounts details";
    }
}
