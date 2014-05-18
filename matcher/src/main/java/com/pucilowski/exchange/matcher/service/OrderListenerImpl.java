package com.pucilowski.exchange.matcher.service;

import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.integration.service.server.OrderListener;
import com.pucilowski.exchange.matcher.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by martin on 18/05/14.
 */

@Service
public class OrderListenerImpl implements OrderListener {

    @Autowired
    Matcher matcher;

    @Override
    public void orderSubmitted(OrderSubmitted order) {
        Order o = new Order(order);
        matcher.inputOrder(o);
    }

    @Override
    public void orderCancelled(OrderCancelled order) {

    }
}
