package com.pucilowski.exchange.integration.service.server;

import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;

/**
 * Created by martin on 18/05/14.
 */
public interface OrderListener {

    public void orderSubmitted(OrderSubmitted order);

    public void orderCancelled(OrderCancelled order);

}
