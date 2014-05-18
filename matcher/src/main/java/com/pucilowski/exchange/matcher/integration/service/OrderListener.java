package com.pucilowski.exchange.matcher.integration.service;

import com.pucilowski.exchange.matcher.integration.request.OrderCancelled;
import com.pucilowski.exchange.matcher.integration.request.OrderSubmitted;

/**
 * Created by martin on 03/02/14.
 */
public interface OrderListener {
    void orderSubmitted(OrderSubmitted order);

    void orderCancelled(OrderCancelled order);
}
