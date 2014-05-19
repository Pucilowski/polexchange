package com.pucilowski.exchange.main.web.controller.api;

import com.pucilowski.exchange.api.user.request.SubmitOrder;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Collection<Order> getOrders() {
        return userService.getOrders();
    }

    @RequestMapping(value = "/orders/", method = RequestMethod.POST)
    public OrderSubmitted submitOrder(@RequestBody SubmitOrder order) {
        return userService.submitOrder(order);
    }

    @RequestMapping(value = "/orders/{id}/", method = RequestMethod.DELETE)
    public void cancelOrder(@PathVariable Long id) {
        userService.cancelOrder(id);
    }

    @RequestMapping(value = "/trades/", method = RequestMethod.GET)
    public Collection<Trade> getTrades() {
        return userService.getTrades();
    }
}
