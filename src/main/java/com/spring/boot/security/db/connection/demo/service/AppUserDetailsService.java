package com.spring.boot.security.db.connection.demo.service;


import com.spring.boot.security.db.connection.demo.model.AppUser;
import com.spring.boot.security.db.connection.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        AppUser user= userRepository.findByUsername(username);
        String password = null;
        if(user == null){
            throw new UsernameNotFoundException("User details not found for the User :  " + username);
        }else{
          password = user.getPassword();
          grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        }
        return new User(username, password, grantedAuthorities);
    }
}