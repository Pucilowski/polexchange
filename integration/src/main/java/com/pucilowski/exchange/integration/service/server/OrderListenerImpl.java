package com.pucilowski.exchange.integration.service.server;

import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import org.springframework.stereotype.Service;

/**
 * Created by martin on 18/05/14.
 */

@Service
public class OrderListenerImpl implements OrderListener {
    @Override
    public void orderSubmitted(OrderSubmitted order) {
        System.out.println("Order submitted: " + order);
    }

    @Override
    public void orderCancelled(OrderCancelled order) {
        System.out.println("Order cancelled: " + order);
    }
}
