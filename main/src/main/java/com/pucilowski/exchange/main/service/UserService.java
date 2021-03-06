package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.api.user.request.SubmitOrder;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */
public interface UserService {
    Collection<Order> getOrders(Principal principal);

    OrderSubmitted submitOrder(UserDetails principal, SubmitOrder dto);

    void cancelOrder(Principal principal, Long id);

    Collection<Trade> getTrades(Principal principal);

    User registerUser(String email, String password);
}
