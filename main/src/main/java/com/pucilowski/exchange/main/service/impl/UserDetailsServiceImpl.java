package com.pucilowski.exchange.main.service.impl;

import com.pucilowski.exchange.main.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by martin on 19/05/14.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.pucilowski.exchange.main.persistence.entity.User user = userRepository.findByEmail(username);
        //return new Username(user, user.getPassword(), Collections.<GrantedAuthority>emptyList());
        if (user == null) return null;

        return new User(user.getEmail(), user.getPassword(), Collections.<GrantedAuthority>emptyList());
    }

}
