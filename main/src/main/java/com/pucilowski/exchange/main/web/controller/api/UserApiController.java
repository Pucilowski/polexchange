package com.pucilowski.exchange.main.web.controller.api;

import com.pucilowski.exchange.api.user.request.SubmitOrder;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */

@RestController
@RequestMapping(value = "/api/user")
public class UserApiController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/orders/", method = RequestMethod.GET)
    public Collection<Order> getOrders(Principal principal) {
        return userService.getOrders(principal);
    }

    @RequestMapping(value = "/orders/", method = RequestMethod.POST)
    public OrderSubmitted submitOrder(UserDetails principal,
                                      @RequestBody SubmitOrder order) {
        return userService.submitOrder(principal, order);
    }

    @RequestMapping(value = "/orders/{id}/", method = RequestMethod.DELETE)
    public void cancelOrder(Principal principal,
                            @PathVariable Long id) {
        userService.cancelOrder(principal, id);
    }

    @RequestMapping(value = "/trades/", method = RequestMethod.GET)
    public Collection<Trade> getTrades(Principal principal) {
        return userService.getTrades(principal);
    }
}

