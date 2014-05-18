package com.pucilowski.exchange.matcher.integration.service;

import com.pucilowski.exchange.matcher.integration.request.OrderCancelled;
import com.pucilowski.exchange.matcher.integration.request.OrderSubmitted;
import com.pucilowski.exchange.matcher.matching.model.Order;
import com.pucilowski.exchange.matcher.matching.service.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Martin on 29/01/14.
 */

@Service
@Transactional
public class OrderListenerImpl implements OrderListener {

    @Autowired
    Matcher matcher;

    @Override
    public void orderSubmitted(OrderSubmitted order) {
        System.out.println("Order submitted: " + order);

        matcher.inputOrder(new Order(order));
    }

    @Override
    public void orderCancelled(OrderCancelled order) {
        System.out.println("Order cancelled: " + order);

        matcher.cancelOrder(order.getId());
    }
}
