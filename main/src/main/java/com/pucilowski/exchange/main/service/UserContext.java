package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.main.persistence.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by martin on 19/05/14.
 */
public interface UserContext {
    Authentication getAuthentication();

    UserDetails getUserDetails();

    User getUser();
}
