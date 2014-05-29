package com.pucilowski.exchange.main.service.impl;

import com.pucilowski.exchange.main.persistence.entity.User;
import com.pucilowski.exchange.main.service.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by martin on 19/05/14.
 */

@Service
public class UserContextImpl implements UserContext {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UserDetails getUserDetails() {
        return (UserDetails) getAuthentication().getPrincipal();
    }
    @Override
    public User getUser() {
        return null;//getUserDetails();
    }
}
